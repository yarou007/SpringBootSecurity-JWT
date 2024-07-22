package com.management.SpringSecurity_MicroService.Repositories;

import com.management.SpringSecurity_MicroService.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Long> {

    Role findByRole(String role);
}