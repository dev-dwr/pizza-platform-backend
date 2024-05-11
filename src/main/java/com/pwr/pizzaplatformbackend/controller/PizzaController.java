package com.pwr.pizzaplatformbackend.controller;


import com.pwr.pizzaplatformbackend.controller.dto.BucketDto;
import com.pwr.pizzaplatformbackend.controller.dto.PizzaDto;
import com.pwr.pizzaplatformbackend.model.Bucket;
import com.pwr.pizzaplatformbackend.model.Pizza;
import com.pwr.pizzaplatformbackend.repository.BucketRepository;
import com.pwr.pizzaplatformbackend.service.PizzaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1")
@RestController
public class PizzaController {
    private final PizzaService pizzaService;

    @GetMapping("/order/all")
    public ResponseEntity<List<BucketDto>> getAllOrders() {
        List<Bucket> buckets = pizzaService.getAllBuckets();
        List<BucketDto> dto = buckets.stream().map(BucketDto::new).toList();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pizza/all")
    public ResponseEntity<List<PizzaDto>> getAllPizzas() {
        List<Pizza> pizza = pizzaService.getAllPizzas();
        var dto = pizza.stream().map(PizzaDto::new).toList();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pizza/{id}")
    public Pizza getPizzaById(@PathVariable Long id) {
        return pizzaService.findPizzaById(id);
    }

    @GetMapping("/get-order")
    public Bucket getOrder() { //todo: change to get order by email
        var email = "example1@email.com";
        return pizzaService.getUserOrder(email);
    }

}
