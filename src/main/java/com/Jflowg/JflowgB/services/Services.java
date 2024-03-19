package com.Jflowg.JflowgB.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Jflowg.JflowgB.models.Images;
import com.Jflowg.JflowgB.models.Products;
import com.Jflowg.JflowgB.repositories.ImagesRepository;
import com.Jflowg.JflowgB.repositories.ProductReporsitory;

@Service
public class Services {
    private static final String PATH_IMG ="../../../../../resources/static/img/";
    private Long idtemp;

    @Autowired
    ProductReporsitory productReporsitory;

    @Autowired
    ImagesRepository imagesRepository;


    //products
    public Products creaProducts(Products products){
        //idtemp = products.getId();
        return productReporsitory.save(products);
    }

    public void borrarProduct(Long id){
        productReporsitory.deleteById(id);
    }

    public List<Products> listarProducts(){
        return productReporsitory.findAll();
    }

    public Products buscarProductsPorId(Long id){
        return productReporsitory.findById(id).orElse(null);
    }

    //images
    public Images creaImages(Images images){
        return imagesRepository.save(images);
    }
    public void borrarImage(Long id){
        imagesRepository.deleteById(id);
    }
    public List<Images> listarImges(){
        return imagesRepository.findAll();
    }
    public Images buscarImagesPorId(Long id){
        return imagesRepository.findById(id).orElse(null);
    }

}
