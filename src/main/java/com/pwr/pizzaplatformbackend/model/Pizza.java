package com.pwr.pizzaplatformbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "pizza")
@NoArgsConstructor
@Data
public class Pizza {
    @Id
    private Long id;

    private String name; //Pizza identifier based on this we will increment quantity

    @Enumerated(EnumType.STRING)
    private Dough dough;
    @Enumerated(EnumType.STRING)
    private Sauce sauce;
    @Enumerated(EnumType.STRING)
    private Size size;

    private int quantity = 0;

    @ManyToMany
    @JoinTable(
            name = "pizza_ingredients",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<IngredientEntity> ingredientsList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_id")
    @ApiModelProperty(hidden = true)
    @JsonBackReference
    private Bucket bucket;

    private int price;

    public Pizza(String name, Dough dough, Sauce sauce, Size size, List<IngredientEntity> ingredientsList) {
        this.name = name;
        this.dough = dough;
        this.sauce = sauce;
        this.size = size;
        this.ingredientsList = ingredientsList;
    }

    public static PizzaBuilder builder() {
        return new PizzaBuilder();
    }


}
