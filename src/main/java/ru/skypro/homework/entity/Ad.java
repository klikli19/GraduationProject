package ru.skypro.homework.entity;

import lombok.Data;
import javax.persistence.*;

/**
 * ad class, represents an Ad entity
 *
 * @author Gubina Marina
 */
@Entity
@Data
@Table(name = "ads")
public class Ad {
    /**
     * Field: Ab identification number
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Field: description of Ab
     */
    private String description;
    /**
     * Field: price Ab
     */
    private int price;
    /**
     * Field: title Ab
     */
    private String title;

    /**
     * Field: author Ab
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    /**
     * Field: image Ab
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
