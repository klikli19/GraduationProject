package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.skypro.homework.dto.SecurityUserDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class BasicAuthCorsFilter
 * The controller is used for Basic Authentication filter
 * @see OncePerRequestFilter
 * @author Kilikova Anna
 */
@Component
public class BasicAuthCorsFilter extends OncePerRequestFilter {

    @Operation(
            summary = "makes an internal filter",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "allows Access-Control to credentials",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SecurityUserDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Access-Control to credentials failed",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SecurityUserDto.class)
                            )
                    )
            },
            tags = "SecurityUserDto"
    )
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
