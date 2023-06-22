package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.constant.Role;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import javax.sql.DataSource;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final UserDetailsManager manager;

  private final PasswordEncoder encoder;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  private DataSource dataSource;


//  @Override
//  public boolean login(String userName, String password) {
//    log.info("login");
//    if (userRepository.findByPassword(password).isEmpty() &&
//            userRepository.findByPassword(password).isEmpty()) {
//      return false;
//    }
//    UserDetails userDetails = manager.loadUserByUsername(userName);
//    return encoder.matches(password, userDetails.getPassword());
//  }

  @Override
  public boolean login(String userName, String password) {
//    if (!manager.userExists(userName)) {
//      return false;
//    }

//    return encoder.matches(password, userDetails.getPassword());
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();

    jdbcUserDetailsManager.setDataSource(dataSource);
    if (jdbcUserDetailsManager.userExists(userName)) {
      UserDetails userDetails = manager.loadUserByUsername(userName);
      return encoder.matches(password,userDetails.getPassword());
    }
    return false;
  }

  @Override
  public boolean register(RegisterReq registerReq, Role role) {
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