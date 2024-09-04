package com.Coding.SecurityApplication.SecurityApplication.DTO;

import com.Coding.SecurityApplication.SecurityApplication.entities.Role;
import lombok.*;

import java.util.Set;

@Data
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class SignUpDTO {
    private String email;
    private String password;
    private String name;
    private Set<Role> roles;

//    private String getEmail() {
//        return this.email;
//    }
//
//    private String getPassword() {
//        return this.password;
//    }
//    private String getName() {
//        return this.name;
//    }
//
//    private void setEmail(String email) {
//        this.email = email;
//    }
//    private void setPassword(String password) {
//        this.password = password;
//    }
//    private void setName(String name) {
//        this.name = name;
//    }
//
//

}


