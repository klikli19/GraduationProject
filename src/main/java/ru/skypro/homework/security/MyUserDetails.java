package ru.skypro.homework.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.skypro.homework.dto.SecurityUserDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class MyUserDetails implements UserDetails {

    private SecurityUserDto securityUserDto;

    public void setUserDto(SecurityUserDto userDto){
        this.securityUserDto = userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(
                securityUserDto.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getPassword)
                .orElse(null);
    }

    @Override
    public String getUsername() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getEmail)
                .orElse(null);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getIdUserDto(){
        return securityUserDto.getId();
    }
}
