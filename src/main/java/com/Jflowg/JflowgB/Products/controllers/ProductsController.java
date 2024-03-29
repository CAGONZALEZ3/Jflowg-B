package com.Jflowg.JflowgB.Products.controllers;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Jflowg.JflowgB.Products.repositories.ProductReporsitory;
import com.Jflowg.JflowgB.Products.services.Services;
import com.Jflowg.JflowgB.exception.ResourceNotFoundException;
import com.Jflowg.JflowgB.Products.models.Products;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1")
public class ProductsController {

    @Autowired
    private ProductReporsitory productReporsitory;

    @Autowired Services pServices;

    @PostMapping(value = "pro")
    public String login() {
        return "pro";
    }

    @GetMapping("/products")
    public List<Products> listarProducts(){
        return productReporsitory.findAll();
    }

    @PostMapping("/products")
    public Products guardaProducts(@RequestPart("data") Products products,@RequestParam("file") MultipartFile file) throws IOException{
        return pServices.creaProducts(products, file);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> listarProductsPorId(@PathVariable Long id){
        Products products = productReporsitory.findById(id)
                            .orElseThrow(()-> new ResourceNotFoundException("El cliente con esa ID no existe: "+id));
        return ResponseEntity.ok(products);
    }

    @PutMapping("products/{id}")
    public ResponseEntity<Products> actualizarCliente(@PathVariable Long id,@RequestBody Products productsRequest){
        Products products = productReporsitory.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("El cliente con esa ID no existe: "+id));
        
        products.setP_t_i(productsRequest.getP_t_i());
        products.setName(productsRequest.getName());
        products.setDescription(productsRequest.getDescription());
        products.setImage(productsRequest.getImage());
        products.setCantidad(productsRequest.getCantidad());
        products.setPrecio(productsRequest.getPrecio());
        products.setDescuento(productsRequest.getDescuento());

        Products productsActualizado = productReporsitory.save(products);
        return ResponseEntity.ok(productsActualizado);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarProducts(@PathVariable Long id){
        Products products = productReporsitory.findById(id)
        .orElseThrow(()-> new ResourceNotFoundException("El cliente con esa ID no existe: "+id));

        productReporsitory.delete(products);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    
}
