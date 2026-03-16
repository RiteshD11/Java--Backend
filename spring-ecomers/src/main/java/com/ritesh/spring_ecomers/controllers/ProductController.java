package com.ritesh.spring_ecomers.controllers;


import com.ritesh.spring_ecomers.model.product;
import com.ritesh.spring_ecomers.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin // by adding this we can receive the request from the another port numbers also like from the react which i running from another port
public class ProductController {

    @Autowired
    private Service service;

    @GetMapping("/products")
    public List<product> getProduct(){
       return service.getProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<product> getProduct(@PathVariable int id){

        product p=service.getProduct(id);
        if(p.getId()>0){
            return new  ResponseEntity<>(p,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getimage(@PathVariable int id){

       return new ResponseEntity<>( service.getProduct(id).getImageData(),HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addproduct(@RequestPart product product, @RequestPart MultipartFile imageFile){

        try {
            product a= service.addproduct(product,imageFile);
            return new ResponseEntity<>(a,HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }


    }

    // here there is new thing that is setting the response entity means
    /*
    in the  http the request are 200 and 404 and many more we can
    give our there

     */
    //lets see with the example

    /*
    @GetMapping("/products")
    public ResponseEntity<List<product> > getProduct(){
        return new ResponseEntity(service.getProducts(), HttpStatus.ACCEPTED);

    }
    */


}
