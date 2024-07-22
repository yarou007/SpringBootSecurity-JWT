package com.management.SpringSecurity_MicroService.services;

import com.management.SpringSecurity_MicroService.Repositories.RoleRepository;
import com.management.SpringSecurity_MicroService.Repositories.UserRepository;
import com.management.SpringSecurity_MicroService.entities.Role;
import com.management.SpringSecurity_MicroService.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class UserServiceImp implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
       User user = userRepository.findByUsername(username);
       Role role = roleRepository.findByRole(rolename);
       user.getRoles().add(role);
       return user;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
