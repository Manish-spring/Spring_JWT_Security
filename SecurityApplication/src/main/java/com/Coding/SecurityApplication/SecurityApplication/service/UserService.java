package com.Coding.SecurityApplication.SecurityApplication.service;

import com.Coding.SecurityApplication.SecurityApplication.DTO.LoginDTO;
import com.Coding.SecurityApplication.SecurityApplication.DTO.SignUpDTO;
import com.Coding.SecurityApplication.SecurityApplication.DTO.UserDTO;
import com.Coding.SecurityApplication.SecurityApplication.config.PasswordConfig;
import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import com.Coding.SecurityApplication.SecurityApplication.exception.RecordAlreadyAvailableException;
import com.Coding.SecurityApplication.SecurityApplication.exception.RecordNotFoundException;
import com.Coding.SecurityApplication.SecurityApplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Optional;


@Slf4j
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,JwtService jwtService,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername method username -->>>> " + username);
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceAccessException("User is not valid"));
    }

    public User getUserById(Long userId) {
        log.info("Inside getUserById to fetch the User Information");
        return userRepository.findById(userId).get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public UserDTO signUp(SignUpDTO signUpDTO) throws RecordAlreadyAvailableException {

        ModelMapper modelMapper = new ModelMapper();

        log.info("Inside signUp method:");
        Optional<User> ab = userRepository.findByEmail(signUpDTO.getEmail());

        if(ab.isPresent()) {
            log.info("User record already available with email id --" +  signUpDTO.getEmail());
            throw new RecordAlreadyAvailableException("User record already available with email id --" + signUpDTO.getEmail());
        }

        User response = modelMapper.map(signUpDTO,User.class);
        response.setPassword(passwordEncoder.encode(response.getPassword()));
        User abc = userRepository.save(response);

        UserDTO userResp = modelMapper.map(abc,UserDTO.class);

        return userResp;
    }

//    public String login(LoginDTO loginDTO) throws RecordNotFoundException {
//        log.info("Going to fetch the token inside token method for email --" + loginDTO.getEmail());
//        Optional<User> response = userRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
//
//        if(response.isEmpty()){
//            log.info("User record is not available for email id - " + loginDTO.getEmail());
//            throw new RecordNotFoundException("User record is not available for email id - " + loginDTO.getEmail());
//        }
//
//        String tokenValue = jwtService.generateToken(response.get());
//        log.info("Generated Token Value --" + tokenValue);
//        return tokenValue;
//    }
}
