package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageServiceImpl imageService;

    @Override
    public UserDTO updateUser(UserDTO userDTO, Authentication authentication) {
        return null;
    }

    @Override
    public UserDTO getAuthorizedUserDto(Authentication authentication) {
        return userMapper.toDTO(userRepository.getUserByEmail(authentication.getName()));
    }

    @Override
    public User getAuthorizedUser(Authentication authentication) {
        return userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void changePassword(NewPasswordDTO newPasswordDTO, Authentication authentication) {
        User user = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setPassword(newPasswordDTO.getNewPassword());
        userRepository.save(user);
        userMapper.toDTO(user);
    }

    @Override
    public void updateAvatar(MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        /*if (user.getImage() != null) {
            imageService.deleteImage(user.getImage().getId());
        }*/
        user.setImage(imageService.downloadImage(image));
        userRepository.save(user);
        userMapper.toDTO(user);
    }

    @Override
    public byte[] getUserImage(Long userId) {
        return userRepository.findById(userId).get().getImage().getData();
    }
}
