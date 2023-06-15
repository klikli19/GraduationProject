package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ResponseWrapper<T> {

    private final int count;
    private final Collection<T> result;

    public ResponseWrapper(Collection<T> result) {
        this.count = result.size();
        this.result = result;
    }
}
