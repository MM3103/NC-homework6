package com.hw6.repository;

import com.hw6.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select distinct book_title, book_price from book", nativeQuery = true)
    List<Object[]> findTitleAndPrices();

    @Query(value = "select book_title, book_price from book where (book_title like %'Windows'%) or (book_price > 20000) order by book_title asc, book_price desc", nativeQuery = true)
    List<Object[]> findWindowsOr20000();

    @Query(value = "select book_title, book_storage, book_quantity, book_price from book b join purchase p on (b.book_id = p.purchase_book and b.book_quantity > 10) join shop s on (s.shop_id = p.purchase_shop and s.shop_location = b.book_storage) order by book_price asc", nativeQuery = true)
    List<Object[]> findPurchasedInfo();
}
