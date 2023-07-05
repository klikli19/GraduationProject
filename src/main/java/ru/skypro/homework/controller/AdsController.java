package ru.skypro.homework.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.Image;
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
@Tag(name = "Ads")
public class AdsController {
    private final AdService adService;

    @Operation(
            summary = "Get all ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All ads received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapper.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No ads received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapper.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<ResponseWrapper<AdsDTO>> getAllAds(@RequestParam(required = false) String title){
        ResponseWrapper<AdsDTO> response =
                    new ResponseWrapper<>(adService.getAllAds(title));
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Create ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad successfully created",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Failed to create ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDTO> createAds(@RequestPart("properties") @NotNull CreateAdsDTO createAdsDTO,
                                            @RequestPart MultipartFile image,
                                            @NotNull Authentication authentication){

        return ResponseEntity.ok(adService.createAd(createAdsDTO,image,authentication));
    }

    @Operation(
            summary = "Get Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FullAdsDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Don't get Ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = FullAdsDto.class)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAd(@PathVariable int id){
        FullAdsDto fullAdsDto = adService.getFullAd((long) id);
        return ResponseEntity.ok(fullAdsDto);
    }

    @Operation(
            summary = "Delete Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad successfully removed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Failed to delete ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            }
    )
    @PreAuthorize("@adServiceImpl.getFullAd(#id).email == authentication.name or hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id){
        adService.deleteAd((long)id);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Update Ad",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad successfully updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Failed to updated ad",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            }
    )
    @PreAuthorize("@adServiceImpl.getFullAd(#id).email == authentication.name or hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<AdsDTO> updateAd(@PathVariable int id,
                                           @RequestBody CreateAdsDTO createAdsDTO){
        return ResponseEntity.ok(adService.updateAd(createAdsDTO,(long)id));
    }

    @Operation(
            summary = "Get my ads",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "My ads have been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapper.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "My ads have not been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseWrapper.class)
                            )
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<ResponseWrapper<AdsDTO>> getMeAd(){
        ResponseWrapper<AdsDTO> response =
                    new ResponseWrapper<>(adService.getUserAllAds());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update the ad image",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Ad image updated successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error updating ad image",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AdsDTO.class)
                            )
                    )
            }
    )
    @PreAuthorize("@adServiceImpl.getFullAd(#id).email == authentication.name or hasRole('ADMIN')")
    @PatchMapping(value = "/{id}/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImageAd(@PathVariable int id, @RequestPart MultipartFile image) throws IOException {
        return ResponseEntity.ok(adService.updateImage((long) id,image));
    }

    @Operation(
            summary = "Get an image by id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Image received by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Image.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Image by id not received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = Image.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable long id){
        return ResponseEntity.ok(adService.getAdImage(id));
    }
}
