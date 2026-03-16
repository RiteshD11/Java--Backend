package com.ritesh.spring_ecomers.repository;

import com.ritesh.spring_ecomers.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepo extends JpaRepository<product,Integer> {

}
