package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.entity.Ad;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.service.AdService;

import java.util.Collection;

@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    public AdServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public Collection<AdsDTO> getAllAds(String title) {
        if (title == null){
            return null;
        }

        return AdsMapper.INSTANCE.adsToAdsListDto(
                adRepository.findByTitleLikeIgnoreCase(title));
    }

    @Override
    public AdsDTO createAd(CreateAdsDTO createAdsDTO, Image image) {
        if(createAdsDTO == null){
            return null;
        }
        Ad ad = AdsMapper.INSTANCE.adsDtoToAd(createAdsDTO);
        ad.setImage(image);
        adRepository.save(ad);

        return AdsMapper.INSTANCE.adToAdsDTO(ad);
    }

    @Override
    public FullAdsDto getFullAd(long adId) {
        return AdsMapper.INSTANCE.adToFullAdsDto(
                adRepository.findById(adId).orElseThrow(AdNotFoundException::new));
    }

    @Override
    public void deleteAd(long adId) {
        adRepository.deleteById(adId);
    }

    @Override
    public AdsDTO updateAd(CreateAdsDTO createAdsDTO, long adId) {
        return null;
    }

    @Override
    public Collection<AdsDTO> getUserAllAds(long userId) {
        return null;
    }

    @Override
    public String updateImage(long adId, Image image) {
        return null;
    }
}
