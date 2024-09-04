package com.Coding.SecurityApplication.SecurityApplication.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

//@Data
@Getter
@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private  Long id;
    private String email;
    private String password;
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream().map(e -> new SimpleGrantedAuthority("ROLE_" + e.name())).collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

//    public Long getId() {
//        return this.id;
//    }

//    public void setId(Long id) {
//       this.id = id;
//    }
////    public void setName(String name) {
////        this.name = name;
////    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
