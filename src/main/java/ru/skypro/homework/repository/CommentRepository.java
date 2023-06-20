package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Comment;

import java.util.List;

/**
 * Repository CommentRepository
 * The repository is used to work with the database
 * The repository is inherited from {@link JpaRepository}
 * @author Bogomolov Ilya
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByAdId(long id);
    void deleteByIdAndAdId(long adId, long commentId);

    Comment findByIdAndAd_Id(long adId, long commentId);

}
