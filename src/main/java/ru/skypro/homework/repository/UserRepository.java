package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Interface UserRepository
 * The interface is used to work with the database
 * @author Kilikova Anna
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);
    User getUserByEmail(String email);
}
