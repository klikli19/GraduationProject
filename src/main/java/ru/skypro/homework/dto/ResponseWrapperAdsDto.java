package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperAdsDto {

    private int count;
    private List<AdsDTO> results;

    public ResponseWrapperAdsDto(List<AdsDTO> results) {
        this.count = results.size();
        this.results = results;
    }
}
