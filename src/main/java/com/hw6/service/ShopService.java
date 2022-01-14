package com.hw6.service;


import com.hw6.exсeption.ResourceNotFoundException;
import com.hw6.model.Shop;
import com.hw6.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopRepository repository;

    public void delete(Integer id) throws ResourceNotFoundException {
        Shop shop = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shop not found for id: " + id));
        repository.delete(shop);
    }

    public Shop update(Integer id, Shop newShop) throws ResourceNotFoundException {
        Shop shop = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shop not found for id: " + id));
        if (newShop.getName() != null) shop.setName(newShop.getName());
        if (newShop.getLocation() != null) shop.setLocation(newShop.getLocation());
        if (newShop.getCommission() != 0) shop.setCommission(newShop.getCommission());
        return repository.save(shop);
    }

    public Shop add(Shop newShop) {
        return repository.save(newShop);
    }

    public List<Shop> getAll() {
        return repository.findAll();
    }

    public Shop get(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shop not found for id: " + id));
    }

    public Shop replace(Integer id, Shop newShop) throws ResourceNotFoundException {
        Shop shop = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shop not found for id: " + id));
        shop.setName(newShop.getName());
        shop.setLocation(newShop.getLocation());
        shop.setCommission(newShop.getCommission());
        return repository.save(shop);
    }

    public List<String> getByLocation(String district) {
        return repository.findByLocation(district);
    }

    public List<String> getCustomersWithSaleBetween10And15() {
        return repository.findCustomersWithSaleBetween10And15();
    }
}
