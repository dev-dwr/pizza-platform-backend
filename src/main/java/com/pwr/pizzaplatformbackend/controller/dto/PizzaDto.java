package com.pwr.pizzaplatformbackend.controller.dto;

import com.pwr.pizzaplatformbackend.model.IngredientEntity;
import com.pwr.pizzaplatformbackend.model.Ingredients;
import com.pwr.pizzaplatformbackend.model.Pizza;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
@Data
public class PizzaDto {

    private Long id;
    private String name;
    private String dough;
    private String sauce;
    private String size;
    private int quantity;
    private List<Ingredients> ingredients;
    private int price;

    public PizzaDto(Pizza pizza) {
        this.id = pizza.getId();
        this.name = pizza.getName();
        this.dough = pizza.getDough().name();
        this.sauce = pizza.getSauce().name();
        this.size = pizza.getSize().name();
        this.quantity = pizza.getQuantity();
        this.ingredients = pizza.getIngredientsList().stream()
                .map(IngredientEntity::getIngredient)
                .collect(Collectors.toList());
        this.price = pizza.getPrice();
    }
}