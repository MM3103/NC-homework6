package com.hw6.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shop")
@Data
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id", nullable = false)
    private int id;

    @Column(name = "s_name", length = 50, nullable = false)
    private String name;

    @Column(name = "s_location", length = 50, nullable = false)
    private String location;

    @Column(name = "s_commission", nullable = false)
    private int commission;
}
