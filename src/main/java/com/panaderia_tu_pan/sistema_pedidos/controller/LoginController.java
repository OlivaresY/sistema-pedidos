package com.panaderia_tu_pan.sistema_pedidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // Esto busca el archivo templates/login.html
    }
}