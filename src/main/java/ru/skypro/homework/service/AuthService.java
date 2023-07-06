package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.constant.Role;

/**
 * Interface AuthService
 * The interface is used to work with login and registration user's
 */
public interface AuthService {

    /**
     * The method used to user authentication
     *
     * @param userName user login
     * @param password user password
     * @return authorized user
     */
    boolean login(String userName, String password);

    /**
     * The method used to user registration
     *
     * @param registerReq registration
     * @param role        role
     * @return registered user
     */
    boolean register(RegisterReq registerReq, Role role);

}
