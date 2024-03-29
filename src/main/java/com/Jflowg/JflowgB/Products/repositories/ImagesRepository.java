package com.Jflowg.JflowgB.Products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jflowg.JflowgB.Products.models.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images,Long>{
    
}
