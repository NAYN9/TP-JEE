package org.sid.costomerservice.Controller;

import org.sid.costomerservice.Services.CustomerService;
import org.sid.costomerservice.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public void add(@RequestBody Customer c){customerService.addCustomer(c);}

    @DeleteMapping("/deleteCustomer/{id}")
    public void delete(@PathVariable Long id){customerService.deleteCustomer(id);}
}
