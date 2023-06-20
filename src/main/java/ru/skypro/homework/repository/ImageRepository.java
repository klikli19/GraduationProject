package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Image;
/**
 * Interface ImageRepository
 * The interface is used to work with the database
 * @author Kilikova Anna
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
