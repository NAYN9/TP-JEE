package org.sid.costomerservice.Services;

import org.sid.costomerservice.Repository.CustomerRepository;
import org.sid.costomerservice.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(Customer c){customerRepository.save(c);}

    public void deleteCustomer(Long id){customerRepository.deleteById(id);}
}
