package com.pwr.pizzaplatformbackend.model;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "ingredient")
@Data
public class IngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Ingredients ingredient;

    public IngredientEntity() {}
}
