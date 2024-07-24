package com.management.SpringSecurity_MicroService.restcontrollers;
import com.management.SpringSecurity_MicroService.Repositories.ProductRepository;
import com.management.SpringSecurity_MicroService.entities.Product;
import com.management.SpringSecurity_MicroService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{idProduct}")
    public Product getProductById(@PathVariable("idProduct") Long idProduct){
        return productService.getProductById(idProduct);
    }
    @GetMapping("/products/category/{idCategory}")
    public List<Product> getAllProductsByCategory(@PathVariable("idCategory") Long idCategory){
        return productService.findAllProductsByIdCategory(idCategory);
    }
    @PostMapping("/products/save")
    public Product createProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    @PutMapping("/products/update")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }
    @DeleteMapping("/products/{idProduct}")
    public void deleteProductById(@PathVariable("idProduct") Long idProduct){
        productService.deleteProductById(idProduct);
    }



}