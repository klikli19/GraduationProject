package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.service.AdService;

import java.util.Collection;

@Service
public class AdServiceImpl implements AdService {
    @Override
    public Collection<ResponseWrapperAdsDto> getAllAds() {
        return null;
    }

    @Override
    public FullAdsDto getFullAd(Integer adsId) {
        return null;
    }

    @Override
    public void deleteAd(Integer adsId) {

    }
}
