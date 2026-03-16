package com.spring.security.service;

import com.spring.security.dao.UserRepo;
import com.spring.security.model.User;
import com.spring.security.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user=  repo.findByUsername(username);
      if(user.getUsername()==null){
          System.out.println("User not found");
          throw new RuntimeException("404");
      }
        return new UserPrincipal(user);
    }
}
