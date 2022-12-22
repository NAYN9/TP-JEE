package org.sid.inventoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public void addProduct(Product p){productRepo.save(p);}

    public void deleteProduct(Long id){productRepo.deleteById(id);}
}
