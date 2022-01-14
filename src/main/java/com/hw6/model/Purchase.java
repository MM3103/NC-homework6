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
    @Column(name = "p_id", nullable = false)
    private int id;

    @Column(name = "p_date", nullable = false)
    private Date date;

    @Column(name = "p_shop", nullable = false)
    private int shop;

    @Column(name = "p_customer", nullable = false)
    private int customer;

    @Column(name = "p_book", nullable = false)
    private int book;

    @Column(name = "p_quantity", nullable = false)
    private int quantity;

    @Column(name = "p_sum", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int sum;
}
