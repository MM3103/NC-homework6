package com.hw6.repository;


import com.hw6.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Query(value = "select distinct to_char(purchase_date, 'Month') from purchase", nativeQuery = true)
    List<String> findMonths();

    @Query(value = "select p.purchase_id, c.customer_name, s.shop_name from purchase p join customer c on (c.customer_id = p.purchase_customer) join shop s on (s.shop_id = p.purchase_shop)", nativeQuery = true)
    List<Object[]> findCustomersAndShops();

    @Query(value = "select p.purchase_id, p.purchase_date, c.customer_name, c.customer_sale, b.book_title, p.purchase_quantity from purchase p join customer c on (c.customer_id = p.purchase_customer) join book b on (b.book_id = p.purchase_book)", nativeQuery = true)
    List<Object[]> findCustomersAndBooks();

    @Query(value = "select p.purchase_id, c.customer_name, p.purchase_date from purchase p join customer c on (c.customer_id = p.purchase_customer) where p.purchase_sum >= 60000", nativeQuery = true)
    List<Object[]> findSumGreaterOrEqualThan60000();

    @Query(value = "select p.purchase_id, c.customer_name, c.customer_residence, p.purchase_date from purchase p join customer c on (c.customer_id = p.purchase_customer) join shop s on (s.shop_id = p.purchase_shop and s.shop_location = c.customer_residence) where p.purchase_date >= cast('2021-03-01' as date) order by p.purchase_date", nativeQuery = true)
    List<Object[]> findInTheSameDistrict();
}
