package com.spring.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.security.model.User;
public interface UserRepo extends JpaRepository<User,Integer> {

    public User findByUsername(String usename);
}