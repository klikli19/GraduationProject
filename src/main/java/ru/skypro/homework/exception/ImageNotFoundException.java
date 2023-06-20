package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception ImageNotFoundException
 * An exception is thrown when no image is found in the database
 * The exception is inherited from {@link RuntimeException}
 * @author Kilikova Anna
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException() {
        super("Image is not found in database");
    }
}
