package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * The image class representing the image entity
 *
 * @author Kilikova Anna
 * @author Rogozin Alexandr
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image")
public class Image {
    /**
     * Field: Image identification number
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    /**
     * Field: file size
     */
    private long fileSize;
    /**
     * Field: file type
     */
    private String mediaType;

    /**
     * Field: image volume
     */
    //@Lob
    private byte[] data;
}
