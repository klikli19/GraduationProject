package ru.skypro.homework.controller;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.impl.AdServiceImpl;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdsController {

    private final AdServiceImpl adService;

    @GetMapping
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds(@RequestParam(required = false) String title){
        ResponseWrapperAdsDto response =
                new ResponseWrapperAdsDto((List<AdsDTO>) adService.getAllAds(title));
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDTO> createAds(@RequestPart("properties") @NotNull CreateAdsDTO createAdsDTO,
                                            @RequestPart MultipartFile image,
                                            @NotNull Authentication authentication){

        return ResponseEntity.ok(adService.createAd(createAdsDTO,image,authentication));
    }

    @GetMapping("{id}")
    public ResponseEntity<FullAdsDto> getAd(@PathVariable int id){
        try {
            FullAdsDto fullAdsDto = adService.getFullAd((long) id);
            return ResponseEntity.ok(fullAdsDto);
        } catch (AdNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id){
        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<AdsDTO> updateAd(@PathVariable int id,
                                           @RequestBody CreateAdsDTO createAdsDTO){
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapperAdsDto> getMeAd(){
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImageAd(@PathVariable int id,
                                           @RequestPart MultipartFile image){
        return ResponseEntity.ok().build();
    }
}
