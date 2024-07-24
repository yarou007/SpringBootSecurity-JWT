package com.management.SpringSecurity_MicroService.security;


import com.auth0.jwt.JWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    AuthenticationManager authenticationManager;

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder,
            UserDetailsService userDetailsService
            ) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(sessionAuthenticationStrategy ->
                sessionAuthenticationStrategy.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsConfigurer -> corsConfigurer
                        .configurationSource(corsConfigurationSource()));
        httpSecurity.authorizeHttpRequests(authorize -> authorize
                .requestMatchers( "/login").permitAll() // Allow public access to /login endpoint
                .requestMatchers(HttpMethod.GET , "/api/users").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET , "/api/products").hasAnyAuthority("ADMIN","CASHIER","USER")
                .requestMatchers(HttpMethod.GET , "/api/products/**").hasAnyAuthority("ADMIN","CASHIER","USER")
                .requestMatchers(HttpMethod.GET , "/api/categories").hasAnyAuthority("ADMIN","CASHIER","USER")
                .requestMatchers(HttpMethod.GET , "/api/categories/**").hasAnyAuthority("ADMIN","CASHIER","USER")
                .requestMatchers(HttpMethod.POST , "/api/products/save").hasAnyAuthority("CREATE","CASHIER")
                .requestMatchers(HttpMethod.PUT , "/api/products/update").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE , "/api/products/delete/**").hasAuthority("ADMIN")

                .anyRequest().authenticated());
        httpSecurity.addFilterBefore(new JWTAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return  httpSecurity.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.setExposedHeaders(Arrays.asList("Authorization"));
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
