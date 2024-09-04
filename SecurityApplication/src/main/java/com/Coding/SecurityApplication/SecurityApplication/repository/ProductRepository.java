package com.Coding.SecurityApplication.SecurityApplication.repository;


import com.Coding.SecurityApplication.SecurityApplication.entities.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findBy( Pageable abc);
}
