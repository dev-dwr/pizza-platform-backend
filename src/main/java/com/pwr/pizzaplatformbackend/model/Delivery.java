package com.pwr.pizzaplatformbackend.model;

public enum Delivery {
    ON_PIZZA_PLACE("On Pizza place"),
    ON_YOUR_HOME("On your home delivery"),
    ;

    private final String description;

    Delivery(String description) {
        this.description = description;
    }
}
