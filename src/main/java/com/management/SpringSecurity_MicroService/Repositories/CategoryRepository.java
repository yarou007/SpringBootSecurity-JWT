package com.management.SpringSecurity_MicroService.Repositories;

import com.management.SpringSecurity_MicroService.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}