package com.management.SpringSecurity_MicroService.Repositories;

import com.management.SpringSecurity_MicroService.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);
}