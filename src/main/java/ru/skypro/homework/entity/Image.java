package ru.skypro.homework.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    @OneToOne(mappedBy = "image")
    private User user;

//    public Image(String name, long fileSize, String mediaType, byte[] data) {
//        this.name = name;
//        this.fileSize = fileSize;
//        this.mediaType = mediaType;
//        this.data = data;
//    }
//    public Image(String name){
//        this.name = name;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Image image = (Image) o;
//        return getId() != null && Objects.equals(getId(), image.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
