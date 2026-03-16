package com.spring.security.sequrityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password4j.BcryptPassword4jPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.AuthProvider;

@Configuration
@EnableWebSecurity
public class configure {


    // we need to verify the token

    @Autowired
    private jwtFilter jwtfilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {


        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/register","/login")
                .permitAll()
                .anyRequest().authenticated())
                .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class);

//        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());


        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();


    }

    @Autowired
    private UserDetailsService userDetailsService;  // the job of this is to load the user details from the database


    @Bean
    public AuthenticationProvider authProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){

        return configuration.getAuthenticationManager();
    }

}