package com.Jflowg.JflowgB.Products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jflowg.JflowgB.Products.models.Products;

@Repository
public interface ProductReporsitory extends JpaRepository<Products,Long> {

    
}
