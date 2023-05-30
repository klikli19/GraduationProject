package ru.skypro.homework.service;


import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;

import java.util.Collection;

public interface AdService {
   Collection<ResponseWrapperAdsDto> getAllAds();
   FullAdsDto getFullAd(Integer adsId);
   void deleteAd(Integer adsId);

}
