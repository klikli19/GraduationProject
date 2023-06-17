package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Comment;

import java.util.Collection;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByAd_Id(int id);
    void deleteByPkAndAd_Id(long adId, long commentId);

    Comment findByPkAndAd_Id(long adId, long commentId);

}
