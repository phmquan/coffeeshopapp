package com.uit.coffeeshop.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="carts")
public class Cart {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int sum;
    @OneToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartDetails> cartDetails;
}
