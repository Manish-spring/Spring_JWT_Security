package com.Coding.SecurityApplication.SecurityApplication.controller;


import com.Coding.SecurityApplication.SecurityApplication.entities.Product;
import com.Coding.SecurityApplication.SecurityApplication.entities.User;
import com.Coding.SecurityApplication.SecurityApplication.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    Logger ab = LoggerFactory.getLogger(ProductController.class);
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/product")
    public List<Product> getAllProducts() {

        User response = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("user details Id --" + response.getId() + " User name --" + response.getUsername() + " Email Id --" + response.getEmail());
        ab.info("Going to call product method");
        return productRepository.findAll();

    }


}
