package ru.skypro.homework.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

  private static final String[] AUTH_WHITELIST = {
          "/ads",
          "/ads/*/image",
          "/users/*/image",
          "/login",
          "/register",
          "/swagger-resources/**",
          "/swagger-ui.html",
          "/v3/api-docs",
          "/webjars/**"
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeHttpRequests(
            (authorization) ->
                authorization
                    .mvcMatchers(HttpMethod.GET, AUTH_WHITELIST)
                    .permitAll()
                    .mvcMatchers("/ads/**", "/users/**")
                    .authenticated())
        .cors()
            .and()
        .httpBasic(withDefaults());
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
