package com.pwr.pizzaplatformbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "bucket")
@Data
public class Bucket {

    @Id
    private Long id;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pizza> pizza = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private Status currentStatus = Status.INIT;

    @Enumerated(EnumType.STRING)
    private Delivery delivery = Delivery.ON_PIZZA_PLACE;

    private int price = 0;
}