package ru.skypro.homework.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.SecurityUserDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * MyUserDetails class
 * MyUserDetails is the implementation of UserDetails
 * provides the necessary information to construct the Authentication object
 *
 * @author Kilikova Anna
 * @author Marina Gubina
 * @see SecurityUserDto
 */
@Component
//@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyUserDetails implements UserDetails {

    private SecurityUserDto securityUserDto;

    /**
     * Constructor
     *
     * @param userDto Dto Security User
     */
    public void setUserDto(SecurityUserDto userDto) {
        this.securityUserDto = userDto;
    }

    /**
     * The method gets the user ID
     *
     * @return gets the user ID
     */
    public Integer getIdUserDto() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getId)
                .orElse(null);
    }

    /**
     * The method gets permissions
     *
     * @return receives credentials
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getRole)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }

    /**
     * The method gets the password
     *
     * @return gets the password
     */
    @Override
    public String getPassword() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getPassword)
                .orElse(null);
    }

    /**
     * The method gets the username
     *
     * @return gets the username - email of user
     */
    @Override
    public String getUsername() {
        return Optional.ofNullable(securityUserDto)
                .map(SecurityUserDto::getEmail)
                .orElse(null);
    }

    /**
     * The method determines that the account has not expired
     *
     * @return true
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * The method determines the account is not blocked
     *
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * The method determines the validity period of the credentials has not expired
     *
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * The method defines enabled
     *
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
