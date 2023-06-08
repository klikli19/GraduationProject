package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.entity.Image;

import java.util.Collection;

public interface AdService {
   Collection<AdsDTO> getAllAds(String title);
   AdsDTO createAd(CreateAdsDTO createAdsDTO, MultipartFile image, Authentication authentication);
   FullAdsDto getFullAd(Long adId);
   void deleteAd(Long adId, Authentication authentication);
   AdsDTO updateAd(CreateAdsDTO createAdsDTO, Long adId, Authentication authentication);
   Collection<AdsDTO> getUserAllAds(Long userId, Authentication authentication);
   String updateImage(Long adId,MultipartFile image, Authentication authentication);
}
