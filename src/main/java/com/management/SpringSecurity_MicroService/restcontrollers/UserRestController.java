package com.management.SpringSecurity_MicroService.restcontrollers;

import com.management.SpringSecurity_MicroService.Repositories.UserRepository;
import com.management.SpringSecurity_MicroService.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }
}
