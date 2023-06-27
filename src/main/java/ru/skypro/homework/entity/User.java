package ru.skypro.homework.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.constant.Role;

import javax.persistence.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * The User class, provides User entity data
 *
 * @author Kilikova Anna
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    /**
     * Field: User identification number
     */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    /**
     * Field: User Name
     */
    private String firstName;
    /**
     * Field: User's last name
     */
    private String lastName;
    /**
     * Field: User's phone number
     */
    private String phone;
    /**
     * Field: user role
     */
    @Enumerated(EnumType.STRING)
    private Role role;
    /**
     * Field: User password
     */
    private String password;
    /**
     * Field: User's email address
     */
    private String email;

    /**
     * Field: image from the User
     */
    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
