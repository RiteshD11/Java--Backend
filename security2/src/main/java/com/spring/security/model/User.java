package com.spring.security.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

// as the dataase table name is diffent so  we do this for
@Table(name = "securitytable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
    private Integer id;
    private String username;
    private String passwords;
}
