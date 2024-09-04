package com.Coding.SecurityApplication.SecurityApplication;


import com.Coding.SecurityApplication.SecurityApplication.DTO.SignUpDTO;
import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import com.Coding.SecurityApplication.SecurityApplication.exception.RecordAlreadyAvailableException;
import com.Coding.SecurityApplication.SecurityApplication.repository.UserRepository;
import com.Coding.SecurityApplication.SecurityApplication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void test_getUserById() {

        log.info("Inside test method");
        User response = new User();
        response.setId(1L);
        response.setPassword("Test");

        Mockito.when(userRepository.findById(any())).thenReturn(Optional.of(response));

        User ab = userService.getUserById(2L);
        assertEquals(1L,ab.getId());
        assertEquals("Test",ab.getPassword());

        assertThat(ab).isNotNull();
    }

    @Test
    void signUp_userNotAvailable_failed() {

        SignUpDTO ab = new SignUpDTO();

        ab.setEmail("abc@gmail.com");
        ab.setPassword("Test123");
        ab.setName("Manish");

        User response = new User();
        response.setId(1L);
        response.setPassword("Test");


        when(userRepository.findByEmail(any())).thenReturn(Optional.of(response));

        try {
            userService.signUp(ab);
        } catch (RecordAlreadyAvailableException e) {
            assertTrue(e.getMessage().startsWith("User record already available with email id"));
        }
    }
}
