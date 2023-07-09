package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

/**
 * The ResponseWrapper class, represents the essence of the response shell
 *
 * @author Gubina Marina
 */
@Data
public class ResponseWrapper<T> {
    /**
     * Field: count
     */
    private final int count;
    /**
     * Field: collection
     */
    private final Collection<T> results;

    /**
     * Constructor with parameters for creating a response shell
     * @param results Collection<T> results
     */
    public ResponseWrapper(Collection<T> results) {
        this.count = results.size();
        this.results = results;
    }
}
