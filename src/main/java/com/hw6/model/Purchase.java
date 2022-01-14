package com.hw6.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "purchase")
@Data
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id", nullable = false)
    private int id;

    @Column(name = "purchase_date", nullable = false)
    private Date date;

    @Column(name = "purchase_shop", nullable = false)
    private int shop;

    @Column(name = "purchase_customer", nullable = false)
    private int customer;

    @Column(name = "purchase_book", nullable = false)
    private int book;

    @Column(name = "purchase_quantity", nullable = false)
    private int quantity;

    @Column(name = "purchase_sum", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int sum;
}
