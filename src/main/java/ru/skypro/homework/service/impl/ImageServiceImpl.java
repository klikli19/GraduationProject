package ru.skypro.homework.service.impl;

import ch.qos.logback.core.util.FileSize;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.repository.FileSystemRepository;
import ru.skypro.homework.repository.ImageDbRepository;
import ru.skypro.homework.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageService imageService;

    final FileSystemRepository fileSystemRepository;
    final ImageDbRepository imageDbRepository;

    public ImageServiceImpl(FileSystemRepository fileSystemRepository, ImageDbRepository imageDbRepository) {
        this.fileSystemRepository = fileSystemRepository;
        this.imageDbRepository = imageDbRepository;
    }

    @Override
    public Long savingAnImage(byte[] data, long date) throws Exception {
        String location = fileSystemRepository.save(data, date);

        return imageDbRepository.save(new Image()).getId();
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
