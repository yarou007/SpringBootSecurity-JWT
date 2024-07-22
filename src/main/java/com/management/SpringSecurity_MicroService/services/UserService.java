package com.management.SpringSecurity_MicroService.services;

import com.management.SpringSecurity_MicroService.entities.Role;
import com.management.SpringSecurity_MicroService.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    User findUserByUserName(String username);

    Role addRole(Role role);

    User addRoleToUser(String username , String rolename);

    List<User> findAllUsers();
}
