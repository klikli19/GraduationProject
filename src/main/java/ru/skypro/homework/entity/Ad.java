package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private int price;
    private String title;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
