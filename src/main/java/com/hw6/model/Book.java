package com.hw6.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_id", nullable = false)
    private int id;

    @Column(name = "b_title", length = 50, nullable = false)
    private String title;

    @Column(name = "b_price", nullable = false)
    private int price;

    @Column(name = "b_storage", length = 50, nullable = false)
    private String storage;

    @Column(name = "b_quantity", nullable = false)
    private int quantity;
}
