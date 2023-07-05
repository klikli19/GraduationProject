package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.Image;
/**
 * Repository ImageRepository
 * The repository is used to work with the database
 * The repository is inherited from {@link JpaRepository}
 * @author Kilikova Anna
 */
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
