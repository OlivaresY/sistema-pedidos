package com.panaderia_tu_pan.sistema_pedidos.controller;

import com.panaderia_tu_pan.sistema_pedidos.service.ProductoService;
import org.springframework.security.core.Authentication; // Necesario para evitar error
import org.springframework.security.core.GrantedAuthority; // Necesario para esAdmin
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos/menu")
    public String verMenu(Model model, Authentication authentication) {
        //pedimos los productos al Service
        model.addAttribute("productos", productoService.listaPrdocutos());

        //verifica si el usuario autenticado para enviar los datos al menú
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
            model.addAttribute("isAdmin", esAdmin(authentication));
        } else {
            //valores por defecto si no hay nadie logueado
            model.addAttribute("username", "Invitado");
            model.addAttribute("isAdmin", false);
        }

        return "productos/menu";
    }

    private boolean esAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));
    }
}