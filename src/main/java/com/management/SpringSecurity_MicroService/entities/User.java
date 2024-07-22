package com.management.SpringSecurity_MicroService.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder //
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    private String username;
    private String password;
    private Boolean enabled;



    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)// lazy -> importer user , n'importer pas les roles avec , ama eager
    @JoinTable(name="user_role", joinColumns = @JoinColumn(name="userId") , inverseJoinColumns = @JoinColumn(name="roleId"))
    private List<Role> roles;
}