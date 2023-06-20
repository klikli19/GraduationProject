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

/**
 * Servic UserServiceImpl
 * Service for the implementation of the user service
 *
 * @see UserRepository
 * @see UserMapper
 * @see ImageServiceImpl
 * @author Kilikova Anna
 * @author Marina Gubina
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageServiceImpl imageService;

    /**
     * The method shows the user update
     * @param userDTO user DTO
     * @param authentication user identification
     * @return null
     */
    @Override
    public UserDTO updateUser(UserDTO userDTO, Authentication authentication) {
        return null;
    }

    /**
     * The method authorizes the user DTO
     * @param authentication user identification
     * @return outputs an authorized DTO user
     */
    @Override
    public UserDTO getAuthorizedUserDto(Authentication authentication) {
        return userMapper.toDTO(userRepository.getUserByEmail(authentication.getName()));
    }

    /**
     * The method authorizes the user
     * @param authentication user identification
     * @return outputs an authorized user
     */
    @Override
    public User getAuthorizedUser(Authentication authentication) {
        return userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
    }

    /**
     * The method will change the password
     * @param newPasswordDTO new DTO password
     * @param authentication user identification
     */
    @Override
    public void changePassword(NewPasswordDTO newPasswordDTO, Authentication authentication) {
        User user = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setPassword(newPasswordDTO.getNewPassword());
        userRepository.save(user);
        userMapper.toDTO(user);
    }

    /**
     * The method updates the avatar
     * @param image product image
     * @param authentication user identification
     * @throws IOException exclusion of input output
     */
    @Override
    public void updateAvatar(MultipartFile image, Authentication authentication) throws IOException {
        User user = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setImage(imageService.downloadImage(image));
        userRepository.save(user);
        userMapper.toDTO(user);
    }

    /**
     * The method gets the volume of the product image by the User ID
     * @param userId User identification number
     * @return outputs the volume of the product image by user ID
     */
    @Override
    public byte[] getUserImage(Long userId) {
        return userRepository.findById(userId).get().getImage().getData();
    }
}
