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
 * The interface is used to work with ads
 * Possibility to  get all Ads, create Ad, get full Ad, delete Ad,
 * update Ad, get User all Ads, update Image, get Ad Image
 *
 * @author Gubina Marina
 */
public interface AdService {

   /**
    * Method for getting all ads
    * @param title available ads
    * @return list of ads
    */
   Collection<AdsDTO> getAllAds(String title);

   /**
    * Method for creating an ad
    *
    * @param createAdsDTO   a DTO declaration is created
    * @param image          product image
    * @param authentication verification procedure
    * @return created ad in DTO ad
    */
   AdsDTO createAd(CreateAdsDTO createAdsDTO, MultipartFile image, Authentication authentication);

   /**
    * Method of getting full ad
    *
    * @param adId identifier ads
    * @return we get full information about the ad
    */
   FullAdsDto getFullAd(Long adId);

   /**
    * Method that removes the ad
    *
    * @param adId identifier ads
    */
   void deleteAd(Long adId);

   /**
    * Method of update ad
    *
    * @param createAdsDTO a DTO declaration is created
    * @param adId         identifier ads
    * @return displays the updated ad
    */
   AdsDTO updateAd(CreateAdsDTO createAdsDTO, Long adId);

   /**
    * Method outputs all the user's ads
    *
    * @return displays all the user's ads
    */
   Collection<AdsDTO> getUserAllAds();

   /**
    * Method updates the product image
    *
    * @param adId  identifier ads
    * @param image product image
    * @return displays an updated product image
    * @throws IOException Exclusion of input output
    */
   String updateImage(Long adId,MultipartFile image) throws IOException;

   /**
    * Method outputs an image of the product by id
    *
    * @param adId identifier ads
    * @return displays an image of the product by its id
    */
   byte[] getAdImage(Long adId);
}
