package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

/**
 * Servic ImageServiceImpl
 * The image processing service downloads the image,
 * deletes the image, receives the image
 *
 * @see ImageRepository
 * @author Rogozin Alexandr
 * @author Kilikova Anna
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;



    /**
     * The method loads the image
     *
     * @param imageFile product image
     * @return displays the saved product image
     * @throws IOException Exclusion of input output
     */
    @Override
    public Image downloadImage(MultipartFile imageFile) throws IOException {
        log.info("Request to avatar upload");
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
        log.info("Request to avatar delete by id {}", id);
        repository.deleteById(id);
    }

    /**
     * the method outputs the image volume
     *
     * @param id image identification number
     * @return returns the volume of the image
     */
    @Override
    public byte[] getImage(Long id) {
        log.info("Request to avatar by id {}", id);
        return repository.findById(id).orElseThrow(ImageNotFoundException::new).getData();
    }
}
