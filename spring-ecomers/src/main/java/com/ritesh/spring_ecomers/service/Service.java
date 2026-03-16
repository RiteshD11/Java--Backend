package com.ritesh.spring_ecomers.service;


import com.ritesh.spring_ecomers.model.product;
import com.ritesh.spring_ecomers.repository.ProductRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service

public class Service {

    @Autowired
    private ProductRepo repo;

    public List<product> getProducts() {
        return repo.findAll();

    }

//    public product getProduct(int id) {
//        return repo.findById(id).orElse(new product());  // as this has the optional return type so
//    }

    public product getProduct(int id) {

 return repo.findById(id).orElse(new product(-1));

    }

    public product addproduct(product p, MultipartFile imageFile) throws IOException {

        p.setImageName(imageFile.getName());
        p.setImageType(imageFile.getContentType());
        p.setImageData(imageFile.getBytes());
        return repo.save(p);
    }
}
