package com.management.SpringSecurity_MicroService.restcontrollers;

import com.management.SpringSecurity_MicroService.Repositories.CategoryRepository;
import com.management.SpringSecurity_MicroService.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CategoryRestController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }


    @GetMapping(value = "/categories/{idCategory}")
    public Category getCategoryById(@PathVariable("idCategory") Long idCategory){
        return categoryRepository.findById(idCategory).get();
    }
}