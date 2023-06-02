package ru.skypro.homework.service;

import ch.qos.logback.core.util.FileSize;
import org.springframework.http.MediaType;
import ru.skypro.homework.entity.Image;

public interface ImageService {
    Image savingAnImage(Image image);
    Image find(Long id);
    Image imageCompression(FileSize fileSize, Long id);
    Image updatingImage(Image image, Long id);
    Image oneTypeOfImages(MediaType mediaType, Long id);
}
