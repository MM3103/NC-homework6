package com.hw6.controller;


import com.hw6.exсeption.ResourceNotFoundException;
import com.hw6.model.Shop;
import com.hw6.service.ShopService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shops")
@Tag(name = "Shop Controller")
public class ShopController {

    @Autowired
    private ShopService service;

    @DeleteMapping("{id}")
    @Operation(summary = "Delete shop by id")
    public Map<String, Boolean> deleteShop(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update shop information by id")
    public Shop updateShop(@PathVariable Integer id, @RequestBody Shop newShop) throws ResourceNotFoundException {
        return service.update(id, newShop);
    }

    @PostMapping
    @Operation(summary = "Add new shop")
    public Shop addShop(@RequestBody Shop newStore) {
        return service.add(newStore);
    }

    @GetMapping
    @Operation(summary = "Get all shops")
    public List<Shop> getAllShop() {
        return service.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get shop by id")
    public ResponseEntity<Shop> getShop(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Replace shop by id")
    public Shop replaceShop(@PathVariable(value = "id") Integer id, @RequestBody Shop newShop) throws ResourceNotFoundException {
        return service.replace(id, newShop);
    }

    @GetMapping("sovetsky")
    @Operation(summary = "Get names of shops from Sovetsky District")
    public List<String> getSovetskyShop() {
        return service.getByLocation("Sovetsky");
    }

    @GetMapping("sormovsky")
    @Operation(summary = "Get names of shops from Sormovsky District")
    public List<String> getSormovskyShop() {
        return service.getByLocation("Sormovsky");
    }

    @GetMapping("customers-sale-between-10-15")
    @Operation(summary = "Get names of shops located in any area, except for Avtozavodsky District, where books were bought by customers with sales between 10% and 15%")
    public List<String> getShopWhereCustomersWithSaleBetween() {
        return service.getCustomersWithSaleBetween10And15();
    }
}
