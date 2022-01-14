package com.hw6.controller;

import com.hw6.exсeption.ResourceNotFoundException;
import com.hw6.model.Book;
import com.hw6.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Controller")
public class BookController {

    @Autowired
    private BookService service;

    @DeleteMapping("{id}")
    @Operation(summary = "Delete book by id")
    public Map<String, Boolean> deleteBook(@PathVariable Integer id) throws ResourceNotFoundException {
        service.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", true);
        return response;
    }

    @PatchMapping("{id}")
    @Operation(summary = "Update book  by id")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book newBook) throws ResourceNotFoundException {
        return service.update(id, newBook);
    }

    @PostMapping
    @Operation(summary = "Add new book")
    public Book addBook(@RequestBody Book newBook) {
        return service.add(newBook);
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public List<Book> getAllBooks() {
        return service.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Get book by id")
    public ResponseEntity<Book> getBook(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Replace book by id")
    public Book replaceBook(@PathVariable(value = "id") Integer id, @RequestBody Book newBook) throws ResourceNotFoundException {
        return service.replace(id, newBook);
    }

    @GetMapping("titles-and-prices")
    @Operation(summary = "Get different titles and prices of books")
    public List<Object[]> getBooksTitlesAndPrices() {
        return service.getTitlesAndPrices();
    }

    @GetMapping("windows-or-20000")
    @Operation(summary = "Get titles and prices of books where the title contains 'Windows' occurs or the price is more than 20,000")
    public List<Object[]> getBooksWindowsOr20000() {
        return service.getWindowsOr20000();
    }

    @GetMapping("purchased-info")
    @Operation(summary = "Get titles, storages, prices and quantity of books purchased in the storage area and held in stock over 10 pieces")
    public List<Object[]> getBooksPurchasedInfo() {
        return service.getPurchasedInfo();
    }
}
