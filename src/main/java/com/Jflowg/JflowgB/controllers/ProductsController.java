package com.Jflowg.JflowgB.controllers;

import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Jflowg.JflowgB.repositories.ProductReporsitory;
import com.Jflowg.JflowgB.exception.ResourceNotFoundException;
import com.Jflowg.JflowgB.models.Products;

@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/api/v1")
public class ProductsController {
    private static final String IMAGE_PATH = "/Users/Camilo Gonzalez/Documents/Programacion/mine/Jflowg-B/src/main/resources/static/img";

    @Autowired
    private ProductReporsitory productReporsitory;

    @PostMapping(value = "pro")
    public String login() {
        return "pro";
    }

    @GetMapping("/products")
    public List<Products> listarProducts(){
        return productReporsitory.findAll();
    }

    @GetMapping("/images/{img}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImg(@PathVariable String img){
        if (img != null && !img.isEmpty()){
            try {// ../../../../../resources/static/img/
                Path fileName = Paths.get(IMAGE_PATH, img);
                byte[] buffer = Files.readAllBytes(fileName);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok()
                        .contentLength(buffer.length)
                        .contentType(MediaType.parseMediaType("image/png"))
                        .body(byteArrayResource);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/products")
    public Products guardaProducts(@RequestBody Products products){
        return productReporsitory.save(products);
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
