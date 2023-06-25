package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ImageServiceImpl imageService;
    private final PasswordEncoder encoder;

    @Override
    public UserDTO updateUser(UserDTO userDTO, Authentication authentication) {
       log.info("Request to update user");
       User user = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
       user.setFirstName(userDTO.getFirstName());
       user.setLastName(userDTO.getLastName());
       user.setPhone(userDTO.getPhone());
       userRepository.save(user);
       return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getAuthorizedUserDto(Authentication authentication) {
        log.info("Request to getting authorized user");
        return userMapper.toDTO(userRepository.getUserByEmail(authentication.getName()));
    }

    @Override
    public User getAuthorizedUser(Authentication authentication) {
        log.info("Request to getting authorized user");
        return userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void changePassword(NewPasswordDTO newPasswordDTO, Authentication authentication) {
        log.info("Request to change password");
        User user = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setPassword(encoder.encode(newPasswordDTO.getNewPassword()));
        userRepository.save(user);
        userMapper.toDTO(user);
    }

    @Override
    public void updateAvatar(MultipartFile image, Authentication authentication) throws IOException {
        log.info("Request to update avatar of user");
        User user = userRepository.findByEmailIgnoreCase(authentication.getName()).orElseThrow(UserNotFoundException::new);
        user.setImage(imageService.downloadImage(image));
        userRepository.save(user);
        userMapper.toDTO(user);
    }

    @Override
    public byte[] getUserImage(Long userId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if(user.getImage() != null){
            return user.getImage().getData();
        }
        else{
            File emptyAvatar = new File("src/main/resources/static/emptyAvatar.png");
            return Files.readAllBytes(emptyAvatar.toPath());
        }
    public byte[] getUserImage(Long userId) {
        log.info("Request to getting image");
        return userRepository.findById(userId).get().getImage().getData();
    }
}
