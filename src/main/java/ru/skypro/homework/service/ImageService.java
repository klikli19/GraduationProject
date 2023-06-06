package ru.skypro.homework.service;

import ch.qos.logback.core.util.FileSize;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.Image;

import java.io.IOException;

public interface ImageService{

    void savingAnImage(MultipartFile file) throws IOException;
    Image find(Long id);
    Image imageCompression(FileSize fileSize, Long id);
    Image updatingImage(Image image, Long id);
    Image oneTypeOfImages(MediaType mediaType, Long id);
}
