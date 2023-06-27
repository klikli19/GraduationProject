package ru.skypro.homework.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(
            summary = "get all ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "all ads received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "no ads received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            },
            tags = "AdsDTO"
    )
    @GetMapping
    public ResponseEntity<ResponseWrapper<AdsDTO>> getAllAds(@RequestParam(required = false) String title){
        ResponseWrapper<AdsDTO> response =
                    new ResponseWrapper<>(adService.getAllAds(title));
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "creates ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "the ad has been created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "does not create ads",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            },
            tags = "AdsDTO"
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDTO> createAds(@RequestPart("properties") @NotNull CreateAdsDTO createAdsDTO,
                                            @RequestPart MultipartFile image,
                                            @NotNull Authentication authentication){

        return ResponseEntity.ok(adService.createAd(createAdsDTO,image,authentication));
    }

    @Operation(
            summary = "get Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ad received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "don't get Ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            },
            tags = "AdsDTO"
    )
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAd(@PathVariable int id){
        FullAdsDto fullAdsDto = adService.getFullAd((long) id);
        return ResponseEntity.ok(fullAdsDto);
    }

    @Operation(
            summary = "delete Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "ad removed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "the ad has not been deleted",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            },
            tags = "AdsDTO"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id){
        adService.deleteAd((long)id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "update Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "the ad has been updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "the ad has not been updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            },
            tags = "AdsDTO"
    )
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDTO> updateAd(@PathVariable int id,
                                           @RequestBody CreateAdsDTO createAdsDTO){
        adService.updateAd(createAdsDTO,(long)id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "get my ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "my ads have been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "my ads have not been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            },
            tags = "AdsDTO"
    )
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapper<AdsDTO>> getMeAd(){
        ResponseWrapper<AdsDTO> response =
                    new ResponseWrapper<>(adService.getUserAllAds());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "update the ad image",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "the ad image has been updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "the ad image has not been updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            },
            tags = "AdsDTO"
    )
    @PatchMapping(value = "/{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImageAd(@PathVariable int id, @RequestPart MultipartFile image) throws IOException {
        return ResponseEntity.ok(adService.updateImage((long) id,image));
    }

    @Operation(
            summary = "get an image by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "image received by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "image by id not received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            },
            tags = "AdsDTO"
    )
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable long id){
        return ResponseEntity.ok(adService.getAdImage(id));
    }

}
