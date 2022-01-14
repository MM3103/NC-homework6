package com.hw6.service;


import com.hw6.exсeption.ResourceNotFoundException;
import com.hw6.model.Book;
import com.hw6.model.Customer;
import com.hw6.model.Purchase;
import com.hw6.model.Shop;
import com.hw6.repository.BookRepository;
import com.hw6.repository.CustomerRepository;
import com.hw6.repository.PurchaseRepository;
import com.hw6.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BookRepository bookRepository;

    public void delete(Integer id) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
        Book book = bookRepository.findById(purchase.getBook()).
                orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + purchase.getBook()));
        book.setQuantity(book.getQuantity() + purchase.getQuantity());
        repository.delete(purchase);
    }

    public Purchase update(Integer id, Purchase newPurchase) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
        if (newPurchase.getDate() == null) newPurchase.setDate(purchase.getDate());
        if (newPurchase.getShop() == 0) newPurchase.setShop(purchase.getShop());
        if (newPurchase.getCustomer() == 0) newPurchase.setCustomer(purchase.getCustomer());
        if (newPurchase.getBook() == 0) newPurchase.setBook(purchase.getBook());
        if (newPurchase.getQuantity() == 0) newPurchase.setQuantity(purchase.getQuantity());
        return updateHelper(newPurchase, purchase);
    }

    public Purchase add(Purchase newPurchase) throws ResourceNotFoundException {
        Book newBook = bookRepository.findById(newPurchase.getBook()).
                orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + newPurchase.getBook()));
        newBook.setQuantity(newBook.getQuantity() - newPurchase.getQuantity());
        newPurchase.setSum(calculateSum(newBook, newPurchase));
        return repository.save(newPurchase);
    }

    public List<Purchase> getAll() {
        return repository.findAll();
    }

    public Purchase get(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
    }

    public Purchase replace(Integer id, Purchase newPurchase) throws ResourceNotFoundException {
        Purchase purchase = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Purchase not found for id: " + id));
        return updateHelper(newPurchase, purchase);
    }

    private int calculateSum(Book newBook, Purchase newPurchase) throws ResourceNotFoundException {
        Shop store = shopRepository.findById(newPurchase.getShop()).
                orElseThrow(() -> new ResourceNotFoundException("Shop not found for id: " + newPurchase.getShop()));
        Customer customer = customerRepository.findById(newPurchase.getCustomer()).
                orElseThrow(() -> new ResourceNotFoundException("Customer not found for id: " + newPurchase.getCustomer()));
        return (int) (newPurchase.getQuantity() * (1 + store.getCommission() / 100d) * (1 - customer.getSale() / 100d) * newBook.getPrice());
    }

    private Purchase updateHelper(Purchase newPurchase, Purchase purchase) throws ResourceNotFoundException {
        Book book = bookRepository.findById(purchase.getBook()).
                orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + purchase.getBook()));
        Book newBook = book;
        if (purchase.getBook() != newPurchase.getBook()) {
            newBook = bookRepository.findById(newPurchase.getBook()).
                    orElseThrow(() -> new ResourceNotFoundException("Book not found for id: " + newPurchase.getBook()));
            book.setQuantity(book.getQuantity() + purchase.getQuantity());
            newBook.setQuantity(newBook.getQuantity() - newPurchase.getQuantity());
        } else {
            book.setQuantity(book.getQuantity() + (purchase.getQuantity() - newPurchase.getQuantity()));
        }
        purchase.setDate(newPurchase.getDate());
        purchase.setShop(newPurchase.getShop());
        purchase.setCustomer(newPurchase.getCustomer());
        purchase.setBook(newPurchase.getBook());
        purchase.setQuantity(newPurchase.getQuantity());
        purchase.setSum(calculateSum(newBook, newPurchase));
        return repository.save(purchase);
    }

    public List<String> getMonths() {
        return repository.findMonths();
    }

    public List<Object[]> getCustomersAndShops() {
        return repository.findCustomersAndShops();
    }

    public List<Object[]> getCustomersAndBooks() {
        return repository.findCustomersAndBooks();
    }

    public List<Object[]> getSumGreaterOrEqualThan60000() {
        return repository.findSumGreaterOrEqualThan60000();
    }

    public List<Object[]> getInTheSameDistrict() {
        return repository.findInTheSameDistrict();
    }
}
