package com.pwr.pizzaplatformbackend.service;

import com.pwr.pizzaplatformbackend.model.Bucket;
import com.pwr.pizzaplatformbackend.model.Pizza;
import com.pwr.pizzaplatformbackend.repository.BucketRepository;
import com.pwr.pizzaplatformbackend.repository.PizzaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {
    private final BucketRepository bucketRepository;
    private final PizzaRepository pizzaRepository;

    public List<Bucket> getAllBuckets() {
        return bucketRepository.findAll();
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza findPizzaById(Long id) {
        return pizzaRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Bucket getUserOrder(String email) {
        return bucketRepository.findByUserEmail(email);
    }
}
