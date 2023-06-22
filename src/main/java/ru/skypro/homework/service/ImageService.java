package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

/**
 * Interface ImageService
 * The interface is used to work with the database
 * @author Kilikova Anna
 */
public interface ImageService {
    Image downloadImage(MultipartFile image) throws IOException;
    void deleteImage(Long id);
    byte[] getImageVolume(Long id);
    byte[] upDateImage(Long id);
}
