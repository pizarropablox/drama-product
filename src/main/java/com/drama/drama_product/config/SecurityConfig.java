package com.drama.drama_product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Bean para el encoder de contraseñas (si es necesario)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configuración de seguridad para el microservicio de productos
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Deshabilitar CSRF si la aplicación es solo RESTful
            .csrf(csrf -> csrf.disable())

            // Definir qué rutas son públicas y cuáles requieren autenticación
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/products/create", "/api/products/all", "/api/products/{id}", "/api/products/{id}/update").permitAll()  // Permite el acceso público a los productos
                .anyRequest().authenticated()  // Requiere autenticación para el resto de las rutas
            )
            
            // Usar la nueva sintaxis Lambda para deshabilitar el formulario de login
            .formLogin(form -> form.disable());  // Deshabilita la página de login nn

        return http.build();
    }
}
