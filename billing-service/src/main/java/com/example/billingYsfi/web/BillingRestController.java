package com.example.billingYsfi.web;

import com.example.billingYsfi.entity.Bill;
import com.example.billingYsfi.entity.BillRequest;
import com.example.billingYsfi.entity.ProductItem;
import com.example.billingYsfi.exeption.ProductItemNotFoundException;
import com.example.billingYsfi.feign.CustomerServiceClient;
import com.example.billingYsfi.feign.InventoryServiceClient;
import com.example.billingYsfi.model.Customer;
import com.example.billingYsfi.model.Product;
import com.example.billingYsfi.repository.BillRepository;
import com.example.billingYsfi.repository.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CustomerServiceClient customerServiceClient;
    private InventoryServiceClient inventoryServiceClient;

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.customerServiceClient = customerServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
    }
    @GetMapping(path="fullBill/{id}")
    public Bill getBill(@PathVariable(name="id") Long id) throws ProductItemNotFoundException {
        Bill bill =billRepository.findById(id).get();

        Customer customer=customerServiceClient.getCustomerById(bill.getCustomerID());
        customerServiceClient.findAll().forEach(System.out::println);
        System.out.println(customer.getName());
        bill.setCustomer(customer);

        bill.getProductItems().forEach(pi->{
            System.out.println("id est "+pi.getId());
            Product product=inventoryServiceClient.getProductById(pi.getProductID());
            pi.setProductName(product.getName());
        });

        return bill;
    }

    @PostMapping("/addbill")
    public void add(@RequestBody BillRequest b){
        System.out.println("AddBill########## "+b.toString());
        Bill bill=billRepository.save(new Bill(null,new Date(),null, b.getCustomerID(),null));
        for (Long id:b.getProductIDs()) {
            Product p = inventoryServiceClient.getProductById(id);
                ProductItem productItem=new ProductItem();
                productItem.setPrice(p.getPrix());
                productItem.setQuantity(p.getQte());
                productItem.setBill(bill);
                productItem.setProductID(p.getId());
                productItemRepository.save(productItem);

        }
    }


}
