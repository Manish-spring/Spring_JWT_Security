package com.Coding.SecurityApplication.SecurityApplication.repository;

import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
}
