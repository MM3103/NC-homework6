package com.hw6.controller;


import com.hw6.exсeption.ResourceNotFoundException;
import com.hw6.model.Customer;
import com.hw6.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@Tag(name = "Customer Controller")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @DeleteMapping("{id}")
    @Operation(summary = "Delete customer by id")
    public Map<String, Boolean> deleteCustomer(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update customer information by id")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer newCustomer) throws ResourceNotFoundException {
        return service.update(id, newCustomer);
    }

    @PostMapping
    @Operation(summary = "Add new customer")
    public Customer addCustomer(@RequestBody Customer newCustomer) {
        return service.add(newCustomer);
    }

    @GetMapping
    @Operation(summary = "Get all customers")
    public List<Customer> getAllCustomers() {
        return service.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get customer by id")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Replace customer by id")
    public Customer replaceCustomer(@PathVariable(value = "id") Integer id, @RequestBody Customer newCustomer) throws ResourceNotFoundException {
        return service.replace(id, newCustomer);
    }

    @GetMapping("districts")
    @Operation(summary = "Get different districts where customers reside")
    public List<String> getCustomersDistricts() {
        return service.getDistricts();
    }

    @GetMapping("nizhegorodsky")
    @Operation(summary = "Get last names and sales of customers from the Nizhegorodsky District")
    public List<Object[]> getNizhegorodskyCustomers() {
        return service.getByResidence("Nizhegorodsky");
    }
}
