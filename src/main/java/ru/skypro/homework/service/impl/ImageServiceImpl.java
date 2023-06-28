package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * Servic ImageServiceImpl
 * Image Processing Service
 *
 * @author Rogozin Alexandr
 * @author Kilikova Anna
 * @see ImageRepository
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

    private final static Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    /**
     * The method loads the image
     *
     * @param imageFile product image
     * @return displays the saved product image
     * @throws IOException Exclusion of input output
     */
    @Override
    public Image downloadImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        return repository.save(image);
    }

    /**
     * image removal method
     *
     * @param id image identification number
     */
    @Override
    public void deleteImage(Long id) {
        repository.deleteById(id);
    }

    /**
     * the method outputs the image volume
     *
     * @param id image identification number
     * @return returns the volume of the image
     */
    @Override
    public byte[] getImageVolume(Long id) {
        return repository.findById(id).orElseThrow(ImageNotFoundException::new).getData();
    }

    public Image getImage(Long id) {
        return repository.findById(id).orElseThrow(ImageNotFoundException::new);
    }

    /**
     * The method outputs an updated product image
     *
     * @param id   image identification number
     * @param file
     * @return displays an updated product image
     */
    @Override
    public Image updateImageAd(Long id, MultipartFile file) {
        log.info("Request to update the image {}", id);
        if (id != null) {
            Image image = getImage(id);
            if (image != null) {
                image.setMediaType(file.getContentType());
                return repository.save(image);
            }
        }
        log.error("The image update did not happen");
        throw new ImageNotFoundException();
    }

    /**
     * image volume compression method
     * @param imageFile product image
     * @return compressedImageFile or imageFile
     * @throws IOException Exclusion of input output
     */
    public File compresssionImageAd(MultipartFile imageFile) throws IOException {
        File input = new File(String.valueOf(downloadImage(imageFile)));

        BufferedImage image = ImageIO.read(input);

        int standard = 1048576;
        if (imageFile.getSize() >= standard) {

            File compressedImageFile = new File("compressed_" + downloadImage(imageFile));
            OutputStream os = new FileOutputStream(compressedImageFile);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer = writers.next();

            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);

            ImageWriteParam param = writer.getDefaultWriteParam();
            float coefficient = standard / (float) imageFile.getSize();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(coefficient);
            writer.write(null, new IIOImage(image, null, null), param);

            os.close();
            ios.close();
            writer.dispose();

            return compressedImageFile;
        } else {
            return (File) imageFile;
        }
    }
}
