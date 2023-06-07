package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Image;

import java.util.Optional;

@Repository
public interface ImageDbRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByUserId(Long userId);
}
