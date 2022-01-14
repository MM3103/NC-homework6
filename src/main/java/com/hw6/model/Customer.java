package com.hw6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    private int id;

    @Column(name = "customer_name", length = 50, nullable = false)
    private String name;

    @Column(name = "customer_residence", length = 50, nullable = false)
    private String residence;

    @Column(name = "customer_sale", nullable = false)
    private int sale;
}
