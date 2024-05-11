package com.pwr.pizzaplatformbackend.controller.dto;


import com.pwr.pizzaplatformbackend.model.Bucket;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BucketDto {

    private Long id;
    private String email;
    private List<PizzaDto> pizzas;
    private String currentStatus;
    private String delivery;
    private int price;

    public BucketDto() {
    }

    public BucketDto(Bucket bucket) {
        this.id = bucket.getId();
        this.email = bucket.getEmail();
        this.pizzas = bucket.getPizza().stream()
                .map(PizzaDto::new)
                .collect(Collectors.toList());
        this.currentStatus = bucket.getCurrentStatus().name();
        this.delivery = bucket.getDelivery().name();
        this.price = bucket.getPrice();
    }
}

