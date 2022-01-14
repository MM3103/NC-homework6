package com.hw6.service;


import com.hw6.exсeption.ResourceNotFoundException;
import com.hw6.model.Customer;
import com.hw6.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public void delete(Integer id) throws ResourceNotFoundException {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + id));
        repository.delete(customer);
    }

    public Customer update(Integer id, Customer newCustomer) throws ResourceNotFoundException {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + id));
        if (newCustomer.getName() != null) customer.setName(newCustomer.getName());
        if (newCustomer.getResidence() != null) customer.setResidence(newCustomer.getResidence());
        if (newCustomer.getSale() != 0) customer.setSale(newCustomer.getSale());
        return repository.save(customer);
    }

    public Customer add(Customer newCustomer) {
        return repository.save(newCustomer);
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Customer get(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + id));
    }

    public Customer replace(Integer id, Customer newCustomer) throws ResourceNotFoundException {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + id));
        customer.setName(newCustomer.getName());
        customer.setResidence(newCustomer.getResidence());
        customer.setSale(newCustomer.getSale());
        return repository.save(customer);
    }

    public List<String> getDistricts() {
        return repository.findDistricts();
    }

    public List<Object[]> getByResidence(String district) {
        return repository.findByResidence(district);
    }
}
