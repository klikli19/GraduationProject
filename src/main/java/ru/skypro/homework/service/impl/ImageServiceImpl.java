package ru.skypro.homework.service.impl;

import ch.qos.logback.core.util.FileSize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.ImageDbRepository;
import ru.skypro.homework.service.ImageService;
import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    @Value("${users.image.dir.path}")
    private String imageDir;

    private ImageService imageService;

    private final UserServiceImpl userService;
    private final ImageDbRepository imageDbRepository;

    public ImageServiceImpl(UserServiceImpl userService, ImageDbRepository imageDbRepository) {
        this.userService=userService;
        this.imageDbRepository = imageDbRepository;
    }

    @Override
    public void uploadImage(Long id, MultipartFile file) throws IOException {
        User user = userService.findUser(id);

        Path filePath = Path.of(imageDir, id + "." +
                getExtension(Objects.requireNonNull(file.getOriginalFilename())));
        Files.createDirectory(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is=file.getInputStream();
        OutputStream os=Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis=new BufferedInputStream(is, 1024);
             BufferedOutputStream bos=new BufferedOutputStream(os, 1024)
        ){
            bis.transferTo(bos);
        }
        Image image=findImage(id);
        image.setUser(user);
        image.setName(filePath.toString());
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        image.setData(generateImagePreview(filePath));

        imageDbRepository.save(image);


    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);

    }
    public Image findImage(Long id){
        return imageDbRepository.findByUserId(id).orElseThrow();
    }
    private byte[] generateImagePreview(Path filePath) throws IOException{
        try (InputStream is=Files.newInputStream(filePath);
        BufferedInputStream bis=new BufferedInputStream(is, 1024);
        ByteArrayOutputStream baos=new ByteArrayOutputStream()){
            BufferedImage image= ImageIO.read(bis);

            int height=image.getHeight()/ image.getHeight()/100;
            BufferedImage preview = new BufferedImage(100,height,image.getType());
            Graphics2D graphics= preview.createGraphics();
            graphics.drawImage(image,0,0,100,height,null);
            graphics.dispose();

            ImageIO.write(preview, getExtension(filePath.getFileName().toString()),baos);
            return baos.toByteArray();
        }
    }

//    @Override
//    public void uploadImage(MultipartFile file) throws IOException{
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        Image image = new Image(fileName, file.getSize(), file.getContentType(), file.getBytes());
//
//        imageDbRepository.save(image);

//    }

    @Override
    public Optional<Image> findById(Long id) {
        return imageService.findById(id);
    }

    @Override
    public Image imageCompression(FileSize fileSize, Long id) {
        return null;
    }

    @Override
    public Image updatingImage(Image image, Long id) {
        return null;
    }

    @Override
    public Image oneTypeOfImages(MediaType mediaType, Long id) {
        return null;
    }
}
