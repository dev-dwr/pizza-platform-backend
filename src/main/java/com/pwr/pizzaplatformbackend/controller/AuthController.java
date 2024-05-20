package com.pwr.pizzaplatformbackend.controller;

import com.pwr.pizzaplatformbackend.security.LoginRequest;
import com.pwr.pizzaplatformbackend.security.LoginResponse;
import com.pwr.pizzaplatformbackend.security.RegistrationRequest;
import com.pwr.pizzaplatformbackend.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("api/v1")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/registration")
    public String register(@RequestBody RegistrationRequest request) {
        authService.register(request);
        return "success";
    }

}
