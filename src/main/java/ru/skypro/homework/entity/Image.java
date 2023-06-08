package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private long fileSize;
    private String mediaType;
    @Lob
    private byte[] data;
}
