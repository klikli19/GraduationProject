package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * the comment class represents the essence of comments
 *
 * @author Bogomolov Ilya
 */
@Entity
@Data
@Table(name = "comments")
public class Comment {
    /**
     * Field:Comment identification number
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Field:  comment creation time
     */
    private LocalDateTime createdAt;
    /**
     * Field: comment text
     */
    private String text;
    /**
     * Field: Ad
     */
    @ManyToOne
    private Ad ad;
    /**
     * Field: the author of the comment
     */
    @ManyToOne
    private User author;

}
