package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.constant.Role;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;
    private String email;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

//    @OneToMany
//    private List<Comment> comment;

}
