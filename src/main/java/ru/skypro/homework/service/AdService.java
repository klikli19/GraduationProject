package ru.skypro.homework.service;


import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Image;

import java.util.Collection;

public interface AdService {
   Collection<AdsDTO> getAllAds(String title);
   AdsDTO createAd(CreateAdsDTO createAdsDTO, Image image);
   FullAdsDto getFullAd(long adId);
   void deleteAd(long adId);
   AdsDTO updateAd(CreateAdsDTO createAdsDTO, long adId);
   Collection<AdsDTO> getUserAllAds(long userId);
   String updateImage(long adId,Image image);
}
