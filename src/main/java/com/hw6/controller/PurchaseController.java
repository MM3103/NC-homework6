package com.hw6.controller;


import com.hw6.exсeption.ResourceNotFoundException;
import com.hw6.model.Purchase;
import com.hw6.service.PurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchases")
@Tag(name = "Purchase Controller")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @DeleteMapping("{id}")
    @Operation(summary = "Delete purchase by id")
    public Map<String, Boolean> deletePurchase(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update purchase information by id")
    public Purchase updatePurchase(@PathVariable Integer id, @RequestBody Purchase newPurchase) throws ResourceNotFoundException {
        return service.update(id, newPurchase);
    }

    @PostMapping
    @Operation(summary = "Add new purchase")
    public Purchase addPurchase(@RequestBody Purchase newPurchase) throws ResourceNotFoundException {
        return service.add(newPurchase);
    }

    @GetMapping
    @Operation(summary = "Get all purchases")
    public List<Purchase> getAllPurchases() {
        return service.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get purchase by id")
    public ResponseEntity<Purchase> getPurchase(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Replace purchase by id")
    public Purchase replacePurchase(@PathVariable(value = "id") Integer id, @RequestBody Purchase newPurchase) throws ResourceNotFoundException {
        return service.replace(id, newPurchase);
    }

    @GetMapping("months")
    @Operation(summary = "Get different months when purchases were made")
    public List<String> getPurchasesMonths() {
        return service.getMonths();
    }

    @GetMapping("customers-and-stores")
    @Operation(summary = "Get last names of customers and names of stores where purchases were made")
    public List<Object[]> getPurchasesCustomersAndStores() {
        return service.getCustomersAndShops();
    }

    @GetMapping("customers-and-books")
    @Operation(summary = "Get dates, last names and sales of customers, names of stores and quantities of purchased books for every purchase")
    public List<Object[]> getPurchasesCustomersAndBooks() {
        return service.getCustomersAndBooks();
    }

    @GetMapping("sum-60000")
    @Operation(summary = "Get order IDs, last names of customers and dates of purchases where sum more than 60,000")
    public List<Object[]> getPurchases60000() {
        return service.getSumGreaterOrEqualThan60000();
    }

    @GetMapping("in-the-same-district")
    @Operation(summary = "Get order IDs, last names and residences of customers and dates of purchases made by customers in their residences no earlier than March")
    public List<Object[]> getPurchasesInTheSameDistrict() {
        return service.getInTheSameDistrict();
    }
}
