package com.example.demoCRUD.Controller;

import com.example.demoCRUD.Exception.CustomerNotFoundException;
import com.example.demoCRUD.Model.Customer;
import com.example.demoCRUD.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private CustomerRepo customerRepo;
    @PostMapping("/customer")
    Customer newCustomer (@RequestBody Customer newCustomer){
        return customerRepo.save(newCustomer);
    }
    @GetMapping("/customers")
    List<Customer> getAllCustomer(){
        return customerRepo.findAll();
    }
    @GetMapping("/customer/{id}")
    Customer getCustomerById(@PathVariable Long id){
        return customerRepo.findById(id)
                .orElseThrow(() ->new CustomerNotFoundException(id));
    }
    @PutMapping("/customer/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable Long id){
        return customerRepo.findById(id)
                .map(customer -> {
                    customer.setName(newCustomer.getName());
                    customer.setEmail(newCustomer.getEmail());
                    customer.setAddress(newCustomer.getAddress());
                    return customerRepo.save(customer);
                }).orElseThrow(()->new CustomerNotFoundException(id));
    }
    @DeleteMapping("/customer/{id}")
    String deleteCustomer(@PathVariable Long id){
        if(!customerRepo.existsById(id)){
            throw new CustomerNotFoundException(id);
        }
        customerRepo.deleteById(id);
        return "Customer with id" + id + "has been delete success";
    }

}
