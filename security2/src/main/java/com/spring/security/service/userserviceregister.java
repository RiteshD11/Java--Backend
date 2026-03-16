package com.spring.security.service;


import com.spring.security.dao.UserRepo;
import com.spring.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userserviceregister {

    @Autowired
    private UserRepo repo;

    public User register(User user){
        repo.save(user);
        return repo.findByUsername(user.getUsername());
    }
}
