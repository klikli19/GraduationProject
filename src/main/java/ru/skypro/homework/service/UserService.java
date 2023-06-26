package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

import java.io.IOException;

public interface UserService {

    UserDTO updateUser(UserDTO userDTO, Authentication authentication);
    UserDTO getAuthorizedUserDto(Authentication authentication);
    User getAuthorizedUser(Authentication authentication);
    void changePassword(NewPasswordDTO newPasswordDTO, Authentication authentication);
    void updateAvatar(MultipartFile image, Authentication authentication) throws IOException;
    byte[] getUserImage(Long adId) throws IOException;
}
