package com.pwr.pizzaplatformbackend.security;

public record RegistrationRequest(String firstname, String lastname, String password, String email, AppUserRole userRole) {}