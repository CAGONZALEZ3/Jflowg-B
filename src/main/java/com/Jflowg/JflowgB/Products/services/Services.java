package com.Jflowg.JflowgB.Products.services;

import java.io.IOException;
import java.util.*;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.Jflowg.JflowgB.FilesUD.FileUploadResponse;
import com.Jflowg.JflowgB.FilesUD.FileUploadUtil;
import com.Jflowg.JflowgB.Products.models.Images;
import com.Jflowg.JflowgB.Products.models.Products;
import com.Jflowg.JflowgB.Products.repositories.ImagesRepository;
import com.Jflowg.JflowgB.Products.repositories.ProductReporsitory;

@Service
public class Services {

    @Autowired
    ProductReporsitory productReporsitory;

    @Autowired
    ImagesRepository imagesRepository;


    //products
    public Products//ResponseEntity<?> 
    creaProducts(Products products, MultipartFile file) throws IOException{

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        //long size = file.getSize();
         
        String filecode = FileUploadUtil.saveFile(fileName, file);

        products.setImage(filecode);
        /* FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + filecode); */
        

        return productReporsitory.save(products);//ResponseEntity.ok("Todo Bien");
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
