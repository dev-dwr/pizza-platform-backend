package com.pwr.pizzaplatformbackend.security;

public record LoginRequest(String password, String email) {}