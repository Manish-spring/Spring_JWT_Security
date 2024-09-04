package com.Coding.SecurityApplication.SecurityApplication.handlers;

import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import com.Coding.SecurityApplication.SecurityApplication.service.AuthService;
import com.Coding.SecurityApplication.SecurityApplication.service.JwtService;
import com.Coding.SecurityApplication.SecurityApplication.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken ab = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User user = (DefaultOAuth2User) ab.getPrincipal();

        String userEmail = user.getAttribute("email");

        User userResp = userService.getUserByEmail(userEmail);
        log.info("User email id " + userEmail);

        String accessToken = jwtService.generateAccessToken(userResp);
        String refreshToken = jwtService.generateRefereshToken(userResp);

        log.info("Refresh Token ---" + refreshToken);
        Cookie cookie = new Cookie("refresh_token",refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        log.info("Before redirect");
        String redirectFrontendUrl = "http://localhost:8081/home.html?token=" + refreshToken;
//        getRedirectStrategy().sendRedirect(request, response, redirectFrontendUrl);

        response.sendRedirect(redirectFrontendUrl);
    }

}
