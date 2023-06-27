package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;

import java.io.IOException;
import java.util.Collection;

/**
 * Interface AdService
 * The interface is used to work with the database
 *
 * @author Gubina Marina
 */
public interface AdService {
   Collection<AdsDTO> getAllAds(String title);
   AdsDTO createAd(CreateAdsDTO createAdsDTO, MultipartFile image, Authentication authentication);
   FullAdsDto getFullAd(Long adId);
   void deleteAd(Long adId);
   AdsDTO updateAd(CreateAdsDTO createAdsDTO, Long adId);
   Collection<AdsDTO> getUserAllAds();
   String updateImage(Long adId,MultipartFile image) throws IOException;
   byte[] getAdImage(Long adId);
}
