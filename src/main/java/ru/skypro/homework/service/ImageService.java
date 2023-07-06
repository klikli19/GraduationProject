package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

/**
 * Interface ImageService
 * The interface is used to work with images
 * The image processing service downloads the image, deletes the image, receives the image
 *
 * @author Kilikova Anna
 */
public interface ImageService {

    /**
     * Method loads the image
     *
     * @param image product image
     * @return displays the saved product image
     * @throws IOException Exclusion of input output
     */
    Image downloadImage(MultipartFile image) throws IOException;

    /**
     * Method of deleting an image
     *
     * @param id image identification number
     */
    void deleteImage(Long id);

    /**
     * Method outputs the image contents
     *
     * @param id image identification number
     * @return returns the contents of the image
     */
    byte[] getImage(Long id);
}
