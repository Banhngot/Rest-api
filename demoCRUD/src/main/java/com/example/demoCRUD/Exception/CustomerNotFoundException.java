package com.example.demoCRUD.Exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(Long id){
        super("Could not found the customer with id" +id);
    }
}
