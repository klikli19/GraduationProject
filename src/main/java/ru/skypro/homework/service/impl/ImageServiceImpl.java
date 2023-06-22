package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

/**
 * Servic ImageServiceImpl
 * Image Processing Service
 *
 * @see ImageRepository
 * @author Rogozin Alexandr
 * @author Kilikova Anna
 */
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;

    private final static Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
    /**
     * The method loads the image
     * @param imageFile product image
     * @return displays the saved product image
     * @throws IOException Exclusion of input output
     */
    @Override
    public Image downloadImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setMediaType(imageFile.getContentType());
        image.setFileSize(imageFile.getSize());
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
     * @param id image identification number
     * @return returns the volume of the image
     */
    @Override
    public byte[] getImageVolume(Long id) {
        return repository.findById(id).orElseThrow(ImageNotFoundException::new).getData();
    }
    @Override
    public byte[] upDateImage(Long id){
       log.info("Request to update the image {}", id);
        if (id != null) {
            byte[] bytes= getImageVolume(id);
            if (bytes != null) {
                return bytes;
            }
        }
        log.error("The image update did not happen");
        throw new ImageNotFoundException();
    }
}
