package ru.skypro.homework.service.impl;

import ch.qos.logback.core.util.FileSize;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.ImageDbRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageService imageService;

    private final ImageDbRepository imageDbRepository;

    public ImageServiceImpl(ImageDbRepository imageDbRepository) {
        this.imageDbRepository = imageDbRepository;
    }

    @Override
    public void savingAnImage(MultipartFile file) throws IOException{
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Image image = new Image(fileName, file.getSize(), file.getContentType(), file.getBytes());

        imageDbRepository.save(image);
    }

    @Override
    public Image find(Long id) {
        return imageService.find(id);
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
