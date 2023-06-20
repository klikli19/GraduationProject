package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.entity.User;

import java.util.Optional;

/**
 * Repository UserRepository
 * The repository is used to work with the database
 * The repository is inherited from {@link JpaRepository}
 * @author Kilikova Anna
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);
    User getUserByEmail(String email);
}
