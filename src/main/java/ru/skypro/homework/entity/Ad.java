package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * Ad entity class
 *
 * @author Gubina Marina
 */
@Entity
@Data
@Table(name = "ads")
public class Ad {
    /**
     * Field: Ad's identification number
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Field: description of Ad
     */
    private String description;
    /**
     * Field: price Ad
     */
    private int price;
    /**
     * Field: title Ad
     */
    private String title;

    /**
     * Field: author Ad
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    /**
     * Field: image Ad
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", author=" + author +
                '}';
    }
}
