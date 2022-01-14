package com.hw6.repository;


import com.hw6.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query(value = "select distinct customer_residence from customer", nativeQuery = true)
    List<String> findDistricts();

    @Query(value = "select customer_name, customer_sale from customer where customer_residence = :district", nativeQuery = true)
    List<Object[]> findByResidence(@Param("district") String district);
}
