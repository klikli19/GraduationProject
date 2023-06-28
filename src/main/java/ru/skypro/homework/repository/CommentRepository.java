package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Comment;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByAdId(long id);
    void deleteByIdAndAdId(long adId, long commentId);
    void deleteAllByAdId(long adId);

    Comment findByIdAndAd_Id(long adId, long commentId);

}
