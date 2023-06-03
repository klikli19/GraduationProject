package ru.skypro.homework.service.impl;

import ch.qos.logback.core.util.FileSize;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {
//    private final Image image;

    private ImageService imageService;
//    public ImageServiceImpl(Image image) {
//        this.image = image;
//    }


    @Override
    public Image savingAnImage(Image image) {
        if (image == null){
            return null;
        }
        return null;
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
