package org.sid.inventoryservice;

import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addproduct")
    private void add(@RequestBody Product p){productService.addProduct(p);}

    @DeleteMapping("/deleteproduct/{id}")
    private void delete(@PathVariable Long id){productService.deleteProduct(id);}
}
