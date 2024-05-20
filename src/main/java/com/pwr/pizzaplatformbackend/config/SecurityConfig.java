package com.pwr.pizzaplatformbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.security.oauth2.client.provider.cognito.issuerUri}")
    private String issuerUri;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/registration", "/api/v1/login", "/api/v1/logout").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer(oAuth2ResourceServerConfigurerCustomizer());

        return http.build();
    }

    private Customizer<OAuth2ResourceServerConfigurer<HttpSecurity>> oAuth2ResourceServerConfigurerCustomizer() {
        JwtDecoder decoder = JwtDecoders.fromIssuerLocation(issuerUri);
        return resourceServerConfigurer -> resourceServerConfigurer.jwt(jwtConfigurer -> jwtConfigurer.decoder(decoder));
    }
}
