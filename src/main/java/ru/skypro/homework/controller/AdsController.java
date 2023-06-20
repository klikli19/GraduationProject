package ru.skypro.homework.controller;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

import java.io.IOException;

/**
 * Controller AdsController
 * The controller is used for receiving all ads, creating ads, get an ad by id,
 * delete an ad by id, update the ad by id, display user ads,
 * updating the product image, display the product image
 * @see AdService
 * @author Gubina Marina
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {
    private final AdService adService;

    /**
     * the method gives all the declarations
     * @param title title
     * @return displays all ads
     */
    @GetMapping
    public ResponseEntity<ResponseWrapper<AdsDTO>> getAllAds(@RequestParam(required = false) String title){
        ResponseWrapper<AdsDTO> response =
                    new ResponseWrapper<>(adService.getAllAds(title));
        return ResponseEntity.ok(response);
    }

    /**
     * The method creates ads
     * @param createAdsDTO creating an ad
     * @param image product image
     * @param authentication authentication
     * @return displays the created ad
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDTO> createAds(@RequestPart("properties") @NotNull CreateAdsDTO createAdsDTO,
                                            @RequestPart MultipartFile image,
                                            @NotNull Authentication authentication){

        return ResponseEntity.ok(adService.createAd(createAdsDTO,image,authentication));
    }

    /**
     * The method issues an ad
     * @param id ad identification number
     * @return displays the ad by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAd(@PathVariable int id){
        FullAdsDto fullAdsDto = adService.getFullAd((long) id);
        return ResponseEntity.ok(fullAdsDto);
    }

    /**
     * The method deletes the ad by id
     * @param id ad identification number
     * @return deletes the selected ad by id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id){
        adService.deleteAd((long)id);
        return ResponseEntity.ok().build();
    }

    /**
     * The method updates the ad by id
     * @param id ad identification number
     * @param createAdsDTO creates a product ad
     * @return displays an updated product ad
     */
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDTO> updateAd(@PathVariable int id,
                                           @RequestBody CreateAdsDTO createAdsDTO){
        adService.updateAd(createAdsDTO,(long)id);
        return ResponseEntity.ok().build();
    }

    /**
     * The method outputs the user's ads
     * @param authentication authentication
     * @return displays the user's ads
     */
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapper<AdsDTO>> getMeAd(@NotNull Authentication authentication){
        ResponseWrapper<AdsDTO> response =
                    new ResponseWrapper<>(adService.getUserAllAds(authentication));
        return ResponseEntity.ok(response);
    }

    /**
     * The method updates the product image
     * @param id ad identification number
     * @param image product image
     * @return displays an updated product image
     * @throws IOException Exclusion of input output
     */
    @PatchMapping(value = "/{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImageAd(@PathVariable int id, @RequestPart MultipartFile image) throws IOException {
        return ResponseEntity.ok(adService.updateImage((long) id,image));
    }

    /**
     * The method outputs an image of the product
     * @param id ad identification number
     * @return displays the product image
     */
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable long id){
        return ResponseEntity.ok(adService.getAdImage(id));
    }

}
