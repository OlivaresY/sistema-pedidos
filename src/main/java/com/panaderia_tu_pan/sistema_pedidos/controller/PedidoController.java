package com.panaderia_tu_pan.sistema_pedidos.controller;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import com.panaderia_tu_pan.sistema_pedidos.service.PedidoService;
import com.panaderia_tu_pan.sistema_pedidos.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public PedidoController(
            PedidoService pedidoService,
            ProductoService productoService) {

        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @GetMapping("/pedidos/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute("pedido", new Pedido());

        model.addAttribute(
                "productos",
                productoService.listaPrdocutos()
        );

        return "pedidos/crear";
    }

    @PostMapping("/pedidos/guardar")
    public String guardarPedido(@Valid @ModelAttribute Pedido pedido, BindingResult result) {
        if (result.hasErrors()) {
            // Si hay errores, regresamos al formulario para que los vea
            return "pedidos/crear";
        }
        pedidoService.guardar(pedido);
        return "redirect:/pedidos/historial";
    }

    @GetMapping("/pedidos/historial")
    public String historial(Model model) {

        model.addAttribute(
                "pedidos",
                pedidoService.listarPedidos()
        );

        return "pedidos/historial";
    }
}
