package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateAdsDTO;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.service.AdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")
public class AdsController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity<ResponseWrapperAdsDto> getAllAds(){
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDTO> createAds(@RequestPart MultipartFile properties,
                                            @RequestPart MultipartFile image){
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<FullAdsDto> getAd(@PathVariable int id){
        return ResponseEntity.ok().build();
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
