package com.Coding.SecurityApplication.SecurityApplication.service;

import com.Coding.SecurityApplication.SecurityApplication.DTO.LoginDTO;
import com.Coding.SecurityApplication.SecurityApplication.DTO.Token;
import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import com.Coding.SecurityApplication.SecurityApplication.exception.RecordNotFoundException;
import com.Coding.SecurityApplication.SecurityApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
//@RequiredArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,UserRepository userRepository,JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public Token login(LoginDTO loginDTO) throws RecordNotFoundException {

        log.info("Inside Login method of AuthService-- " + loginDTO.getEmail());
        Authentication ab = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()
                ));

        User response = (User) ab.getPrincipal();

        if(response == null) {
            log.info("User record is not available for email id - " + loginDTO.getEmail());
            throw new RecordNotFoundException("User record is not available for email id - " + loginDTO.getEmail());
        }
        String accessToken = jwtService.generateAccessToken(response);
        String refreshToken = jwtService.generateRefereshToken(response);
        log.info("Generated access Token Value --" + accessToken);
        log.info("Generated refersh Token Value --" + accessToken);

        Token res = new Token();
        res.setAccessToken(accessToken);
        res.setRefreshToken(refreshToken);
        res.setId(response.getId());
        return res;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
    public Token getTokenViaRefreshMethod(String refreshToken) {

        Long userId = jwtService.generateIdFromToken(refreshToken);

        Optional<User> userRecord = userRepository.findById(userId);

        if(userRecord.isEmpty()) {
            log.info("User Record is not available for this user id --" + userId);
        }

        String tokenValue = jwtService.generateAccessToken(userRecord.get());

        Token tokenResp = new Token();
        tokenResp.setId(userRecord.get().getId());
        tokenResp.setRefreshToken(refreshToken);
        tokenResp.setAccessToken(tokenValue);
        return tokenResp;
    }
}
