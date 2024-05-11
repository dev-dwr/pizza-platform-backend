package com.pwr.pizzaplatformbackend.repository;

import com.pwr.pizzaplatformbackend.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {}
