package com.Coding.SecurityApplication.SecurityApplication.DTO;

import lombok.Data;

@Data
public class Token {

    private Long id;
    private String accessToken;
    private String refreshToken;
}
