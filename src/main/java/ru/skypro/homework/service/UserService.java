package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

import java.io.IOException;

/**
 * Interface UserService
 * The interface is used to work with users
 * Possibility to update user data,  get authorized user Dto, get authorized user,
 * change password, update avatar, get user avatar
 *
 * @author Kilikova Anna
 * @author Marina Gubina
 */
public interface UserService {

    /**
     * The method to update the user
     * @param userDTO user DTO
     * @param authentication user identification
     * @return outputs a user update
     */
    UserDTO updateUser(UserDTO userDTO, Authentication authentication);

    /**
     * Method getting authorized the user DTO
     * @param authentication user identification
     * @return outputs an authorized DTO user
     */
    UserDTO getAuthorizedUserDto(Authentication authentication);

    /**
     * Method authorized the user
     * @param authentication user identification
     * @return outputs an authorized user
     */
    User getAuthorizedUser(Authentication authentication);

    /**
     * Method will change the password
     * @param newPasswordDTO new DTO password
     * @param authentication user identification
     */
    void changePassword(NewPasswordDTO newPasswordDTO, Authentication authentication);

    /**
     * Method updates the avatar
     * @param image product image
     * @param authentication user identification
     * @throws IOException exclusion of input output
     */
    void updateAvatar(MultipartFile image, Authentication authentication) throws IOException;

    /**
     * Method for getting user avatar by id
     * @param userId User identification number
     * @return displays the contents of the user's avatar by id
     * @throws IOException exclusion of input output
     */
    byte[] getUserImage(Long userId) throws IOException;
}
