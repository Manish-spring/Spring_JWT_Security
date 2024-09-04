package com.Coding.SecurityApplication.SecurityApplication.controller;

import com.Coding.SecurityApplication.SecurityApplication.DTO.LoginDTO;
import com.Coding.SecurityApplication.SecurityApplication.DTO.SignUpDTO;
import com.Coding.SecurityApplication.SecurityApplication.DTO.Token;
import com.Coding.SecurityApplication.SecurityApplication.DTO.UserDTO;
import com.Coding.SecurityApplication.SecurityApplication.exception.RecordAlreadyAvailableException;
import com.Coding.SecurityApplication.SecurityApplication.exception.RecordNotFoundException;
import com.Coding.SecurityApplication.SecurityApplication.service.AuthService;
import com.Coding.SecurityApplication.SecurityApplication.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
//@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDTO> signUpUser(@RequestBody SignUpDTO signUpDTO) throws RecordAlreadyAvailableException {
        log.info("Signup Controller program");

        UserDTO response = userService.signUp(signUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping(path = "/login")
    public ResponseEntity<Token> loginUser(@RequestBody LoginDTO loginDTO, HttpServletResponse httpServletResponse) throws RecordNotFoundException {
        log.info("Login for user");
//        String tokenValue = userService.login(loginDTO);

        Token token = authService.login(loginDTO);

        Cookie cookie = new Cookie("refresh_token",token.getRefreshToken());
        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @GetMapping(path = "/refreshToken")
    public ResponseEntity<Token> getRefreshToken(HttpServletRequest request) {
        log.info("Trying to get the access token from the refresh token");

        StringBuilder token = new StringBuilder("");
        for (Cookie a : request.getCookies()) {
            token = new StringBuilder(a.getValue());
        }
//
//        String refreshToken = Arrays.stream(request.getCookies())
//                .filter(row -> "refresh_token".equals(row.getName()))
//                .findFirst()
//                .map(Cookie::getValue).toString();
////
//        log.info("refreshToken token ----" + refreshToken);


        Token tokenResp = authService.getTokenViaRefreshMethod(String.valueOf(token));
        return ResponseEntity.status(HttpStatus.OK).body(tokenResp);
    }
}
