package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;


import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService service;

    @PostMapping("/set_password")
    public ResponseEntity<?> changePassword(@RequestBody NewPasswordDTO newPassword, Authentication authentication) {
        service.changePassword(newPassword, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getAuthorizedUser(Authentication authentication) {
        return ResponseEntity.ok(service.getAuthorizedUserDto(authentication));
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, Authentication authentication) {
        return ResponseEntity.ok( service.updateUser(userDTO, authentication));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatar(@RequestPart MultipartFile image, Authentication authentication) throws IOException {
        service.updateAvatar(image, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable long id){
        log.info("Get user image with id" + id);
        return ResponseEntity.ok(service.getUserImage(id));
    }
}
