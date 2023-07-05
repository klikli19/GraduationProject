package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception AdNotFoundException
 * An exception is thrown when AD is not found in the database
 * The exception is inherited from {@link RuntimeException}
 * @author Marina Gubina
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdNotFoundException extends RuntimeException{

    public AdNotFoundException(){super("Not found AD in database");}
}
