package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.constant.Role;

/**
 * Interface AuthService
 * The interface is used to work with the database
 *
 * @author Kilikova Anna
 */
public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);

}
