package com.pwr.pizzaplatformbackend.model;

import lombok.Getter;

@Getter
public enum Ingredients {
    HAM(true),
    BACON(true),
    SALAMI(true),
    MUSHROOMS(false),
    TOMATO(false),
    PINEAPPLE(false),
    CHILLI(false),
    ;
    private final boolean meat;
    Ingredients(boolean meat) {
        this.meat = meat;
    }
}
