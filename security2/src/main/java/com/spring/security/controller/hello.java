package com.spring.security.controller;

import com.spring.security.model.student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class hello {

    List<student> ls=new ArrayList<>(List.of(
            new student(1,"Ritesh"),
            new student(2,"Ashish")
    ));

    @GetMapping("/")
    public String function(HttpServletRequest req){
        return "Hello World"+req.getSession().getId();
    }

    @GetMapping("/student")
    public List<student> getstudent(){
      return ls;
    }

    @GetMapping("/token")
    public CsrfToken gettoken(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");
    }

    @PostMapping("/student")
    public void function(@RequestBody student std){
        ls.add(std);
    }


}
