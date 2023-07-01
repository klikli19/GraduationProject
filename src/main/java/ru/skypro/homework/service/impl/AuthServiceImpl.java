package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.constant.Role;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.MyUserDetailsService;
import ru.skypro.homework.service.AuthService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final MyUserDetailsService manager;

  private final PasswordEncoder encoder;

  private final UserRepository userRepository;

  private final UserMapper userMapper;


  @Override
  public boolean login(String userName, String password) {
    log.info("Request to user login ");
    UserDetails userDetails = manager.loadUserByUsername(userName);
    return encoder.matches(password, userDetails.getPassword());
  }

  @Override
  public boolean register(RegisterReq registerReq, Role role) {
    log.info("Request to user registration");
    if (userRepository.findByEmailIgnoreCase(registerReq.getUsername()).isPresent()) {
      return false;
    }
    User regUser = userMapper.toEntity(registerReq);
    regUser.setRole(role);
    regUser.setPassword(encoder.encode(regUser.getPassword()));
    userRepository.save(regUser);
    return true;
  }

}