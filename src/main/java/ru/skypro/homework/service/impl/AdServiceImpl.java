package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final ImageService imageService;


    @Override
    public Collection<AdsDTO> getAllAds(String title) {
        if (title == null){
            return AdsMapper.INSTANCE.adsToAdsListDto(adRepository.findAll());
        }

        return AdsMapper.INSTANCE.adsToAdsListDto(
                adRepository.findByTitleLikeIgnoreCase(title));
    }

    @Override
    public AdsDTO createAd(CreateAdsDTO createAdsDTO, MultipartFile image, Authentication authentication) {
        if(createAdsDTO == null){
            throw new RuntimeException("Нет полных данных для создания объявления");
        }
        Image adImage;
        try {
            adImage = imageService.downloadImage(image);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось сохранить фото");
        }

        Ad ad = AdsMapper.INSTANCE.adsDtoToAd(createAdsDTO);
        ad.setImage(adImage);
        //ad.setAuthor();
        adRepository.save(ad);

        return AdsMapper.INSTANCE.adToAdsDTO(ad);
    }

    @Override
    public FullAdsDto getFullAd(Long adId) {
        return AdsMapper.INSTANCE.adToFullAdsDto(
                adRepository.findById(adId).orElseThrow(AdNotFoundException::new));
    }

    @Override
    public void deleteAd(Long adId,Authentication authentication) {
        adRepository.deleteById(adId);
    }

    @Override
    public AdsDTO updateAd(CreateAdsDTO createAdsDTO, Long adId,Authentication authentication) {
        if(adId == null || !adRepository.findById(adId).isPresent()){
            return null;
        }

        Ad ad = AdsMapper.INSTANCE.adsDtoToAd(createAdsDTO);
        adRepository.save(ad);

        return AdsMapper.INSTANCE.adToAdsDTO(ad);
    }

    @Override
    public Collection<AdsDTO> getUserAllAds(Long userId, Authentication authentication) {
        return null;
    }

    @Override
    public String updateImage(Long adId, MultipartFile image, Authentication authentication) {
        if(adId == null){
            return "Ad was not found";
        }
        Ad updateAd = adRepository.findById(adId)
                .orElseThrow(AdNotFoundException::new);
        //updateAd.setImage(image);
        adRepository.save(updateAd);
        return "Photo updated";
    }
}
