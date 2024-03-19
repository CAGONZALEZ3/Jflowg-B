package com.Jflowg.JflowgB.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jflowg.JflowgB.models.Images;

@Repository
public interface ImagesRepository extends JpaRepository<Images,Long>{
    
}
