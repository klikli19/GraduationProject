package ru.skypro.homework.service;

import ch.qos.logback.core.util.FileSize;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;
import java.util.Optional;

public interface ImageService{

    void uploadImage(Long id, MultipartFile file) throws IOException;
    Optional<Image> findById(Long id);
    Image imageCompression(FileSize fileSize, Long id);
    Image updatingImage(Image image, Long id);
    Image oneTypeOfImages(MediaType mediaType, Long id);
}
