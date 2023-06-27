package ru.skypro.homework.controller;

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
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;


import java.io.IOException;

/**
 * Controller UserController
 * The controller is used for changes the password, gets an authorized user,
 * updates the user, updates the avatar, gets an image
 *
 * @see UserService
 * @author Kilikova Anna
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService service;

    @Operation(
            summary = "changes the password",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "password changed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "the password has not been changed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            },
            tags = "UserDTO"
    )
    @PostMapping("/set_password")
    public ResponseEntity<?> changePassword(@RequestBody NewPasswordDTO newPassword, Authentication authentication) {
        service.changePassword(newPassword, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "gets an authorized user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "the authorized user has been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "the authorized user has not been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            },
            tags = "UserDTO"
    )
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getAuthorizedUser(Authentication authentication) {
        return ResponseEntity.ok(service.getAuthorizedUserDto(authentication));
    }

    @Operation(
            summary = "updates the user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "user updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "the user is not updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            },
            tags = "UserDTO"
    )
    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, Authentication authentication) {
        return ResponseEntity.ok( service.updateUser(userDTO, authentication));
    }

    @Operation(
            summary = "updates the avatar",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "avatar updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "the avatar has not been updated",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            },
            tags = "UserDTO"
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatar(@RequestPart MultipartFile image, Authentication authentication) throws IOException {
        service.updateAvatar(image, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "gets an image",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "image received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "image not received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            },
            tags = "UserDTO"
    )
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable long id) throws IOException {
        log.info("Get user image with id" + id);
        return ResponseEntity.ok(service.getUserImage(id));
    }
}
