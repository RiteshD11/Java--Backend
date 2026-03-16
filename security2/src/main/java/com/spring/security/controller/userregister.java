package com.spring.security.controller;


import com.spring.security.model.User;
import com.spring.security.service.jwtService;
import com.spring.security.service.userserviceregister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class userregister {

    @Autowired
    private userserviceregister us;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("register")
    public User register(@RequestBody User user){
        user.setPasswords(encoder.encode(user.getPasswords()));
           return us.register(user);
    }

    @Autowired
    private jwtService jwtservice;
    @PostMapping("/login")
    public String login(@RequestBody User user){

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPasswords()));

        if(authentication.isAuthenticated()) {

            return jwtservice.generateToken(user.getUsername());
//            return "Success";
        }else{
            return "Failed";
        }
    }
}
