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

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepository repository;
    @Override
    public Image downloadImage(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        return repository.save(image);
    }

    @Override
    public void deleteImage(Long id) {
        repository.deleteById(id);
    }

    @Override
    public byte[] getImage(Long id) {
        return repository.findById(id).orElseThrow(ImageNotFoundException::new).getData();
    }
}
