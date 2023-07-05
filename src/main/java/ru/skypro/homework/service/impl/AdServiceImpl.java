package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.security.MyUserDetails;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Collection;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Service AdServiceImpl
 * Service for get all Ads, create Ad, get full Ad, delete Ad,
 * update Ad, get User all Ads, update Image, get Ad Image
 *
 * @author Marina Gubina
 * @see AdsMapper
 * @see AdRepository
 * @see ImageService
 * @see UserService
 * @see CommentService
 * @see MyUserDetails
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AdServiceImpl implements AdService {

    private final AdsMapper adsMapper;
    private final AdRepository adRepository;
    private final ImageService imageService;
    private final UserService userService;
    private final CommentService commentService;
    private final MyUserDetails userDetails;


    /**
     * Method for getting all ads
     * @param title available ads
     * @return list of ads
     */
    @Override
    public Collection<AdsDTO> getAllAds(String title) {
        log.info("Request to receive all ads");
        Collection<Ad> ads;
        if (!isEmpty(title)) {
            ads = adRepository.findByTitleLikeIgnoreCase(title);
        } else {
            ads = adRepository.findAll();
        }
        return adsMapper.adsToAdsListDto(ads);
    }

    /**
     * Method for creating an ad
     *
     * @param createAdsDTO   a DTO declaration is created
     * @param image          product image
     * @param authentication verification procedure
     * @return created ad in DTO ad
     */
    @Override
    public AdsDTO createAd(CreateAdsDTO createAdsDTO, MultipartFile image, Authentication authentication) {
        if (createAdsDTO.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        Ad ad = adsMapper.adsDtoToAd(createAdsDTO);
        ad.setAuthor(userService.getAuthorizedUser(authentication));
        log.info("Request to create new ad");
        Image adImage;
        try {
            adImage = imageService.downloadImage(image);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить фото");
        }
        ad.setImage(adImage);
        adRepository.save(ad);
        log.info("Save new ad ID:" + ad.getId());

        return adsMapper.adToAdsDTO(ad);
    }

    /**
     * Method of getting full ad
     *
     * @param adId identifier ads
     * @return we get full information about the ad
     */
    @Override
    public FullAdsDto getFullAd(Long adId) {
        log.info("Request to get full info about ad");
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        return adsMapper.adToFullAdsDto(ad);
    }

    /**
     * Method that removes the ad
     *
     * @param adId identifier ads
     */
    @Override
    public void deleteAd(Long adId) {
        log.info("Request to delete ad by id");
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        commentService.deleteAllByAdId(adId);
        adRepository.deleteById(adId);
        imageService.deleteImage(ad.getImage().getId());
    }

    /**
     * Method of update ad
     *
     * @param createAdsDTO a DTO declaration is created
     * @param adId         identifier ads
     * @return displays the updated ad
     */
    @Override
    public AdsDTO updateAd(CreateAdsDTO createAdsDTO, Long adId) {
        log.info("Request to update ad by id");
        if (createAdsDTO.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        Ad ad = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        adsMapper.updateAds(createAdsDTO, ad);
        adRepository.save(ad);

        return adsMapper.adToAdsDTO(ad);
    }

    /**
     * Method outputs all the user's ads
     *
     * @return displays all the user's ads
     */
    @Override
    public Collection<AdsDTO> getUserAllAds() {
        log.info("Request to get all user ads");
        Collection<Ad> ads;
        log.info(userDetails.getIdUserDto() + "   " + userDetails.getAuthorities() + "   " + userDetails.getUsername());
        ads = adRepository.findAllAdsByAuthorId(userDetails.getIdUserDto());

        return adsMapper.adsToAdsListDto(ads);
    }

    /**
     * Method updates the product image
     *
     * @param adId  identifier ads
     * @param image product image
     * @return displays an updated product image
     * @throws IOException Exclusion of input output
     */
    @Transactional
    @Override
    public String updateImage(Long adId, MultipartFile image) throws IOException {
        log.info("Request to update image");
        Ad updateAd = adRepository.findById(adId).orElseThrow(AdNotFoundException::new);
        long idImage = updateAd.getImage().getId();
        updateAd.setImage(imageService.downloadImage(image));
        imageService.deleteImage(idImage);
        adRepository.save(updateAd);
        return adsMapper.adToAdsDTO(updateAd).getImage();
    }

    /**
     * Method outputs an image of the product by id
     *
     * @param adId identifier ads
     * @return displays an image of the product by its id
     */
    @Transactional
    @Override
    public byte[] getAdImage(Long adId) {
        log.info("Get image of an AD with a ID:" + adId);
        return imageService.getImage(adRepository.findById(adId).orElseThrow(AdNotFoundException::new).getImage().getId());
    }
}
