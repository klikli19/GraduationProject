package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getAuthorizedUser(Authentication authentication) {
        return null;
    }

    @Override
    public void changePassword(NewPasswordDTO newPasswordDTO) {

    }

    @Override
    public void updateAvatar(MultipartFile image) {

    }

    @Override
    public User findUser(Long userId) {

        return null;
    }
}
