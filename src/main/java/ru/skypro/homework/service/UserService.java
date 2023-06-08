package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {

    UserDTO updateUser(UserDTO userDTO, Authentication authentication);
    UserDTO getAuthorizedUser(Authentication authentication);
    void changePassword(NewPasswordDTO newPasswordDTO);
    void updateAvatar(MultipartFile image);
}
