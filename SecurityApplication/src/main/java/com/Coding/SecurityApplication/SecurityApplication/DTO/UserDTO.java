package com.Coding.SecurityApplication.SecurityApplication.DTO;

import com.Coding.SecurityApplication.SecurityApplication.entities.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {

    private String email;
    private Long id;
    private String name;
    private Set<Role> roles;

//    private String getEmail() {
//        return this.email;
//    }
//
//    private String getName() {
//        return this.name;
//    }
//
//    private void setId(long id) {
//        this.id = id;
//    }
//
//
//    private void setEmail(String email) {
//        this.email = email;
//    }
//
//    private void setName(String name) {
//        this.name = name;
//    }
}
