package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception UserNotFoundException
 * An exception is thrown when no user is found in the database
 * The exception is inherited from {@link RuntimeException}
 * @author Kilikova Anna
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User is not found in database");
    }
}
