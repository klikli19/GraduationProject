package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.SecurityUserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecurityUserDto securityUserDto = userRepository.findByEmailIgnoreCase(username)
                        .map(userMapper::toSecurityDto)
                        .orElseThrow(() -> new UsernameNotFoundException("no username"));
        log.info("load User By Username");
        return new MyUserDetails(securityUserDto);
        /*if(user.isPresent()) {
            User user1 = user.get();
            log.info(String.valueOf(user1));
            return new MyUserDetails(user1);
        } else {
            throw new UsernameNotFoundException("no username");
        }*/
    }
}
