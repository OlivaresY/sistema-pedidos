package com.panaderia_tu_pan.sistema_pedidos.controller;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import com.panaderia_tu_pan.sistema_pedidos.model.Producto;
import com.panaderia_tu_pan.sistema_pedidos.service.PedidoService;
import com.panaderia_tu_pan.sistema_pedidos.service.ProductoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private static final Logger log = LoggerFactory.getLogger(PedidoController.class);
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public PedidoController(PedidoService pedidoService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("productos", productoService.listaPrdocutos());
        return "pedidos/crear";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@Valid @ModelAttribute Pedido pedido, BindingResult result, Model model) {

        pedido.setEstado("NUEVO");

        if (pedido.getProducto() != null && pedido.getProducto().getId() != null) {
            Producto producto = productoService.buscarPorId(pedido.getProducto().getId());
            if (producto != null) {
                pedido.setProducto(producto);
                pedido.setTotal(producto.getPrecio());
            }
        }

        if (result.hasErrors()) {
            log.warn("Errores en formulario de pedido para cliente: {}", pedido.getNombreCliente());
            model.addAttribute("productos", productoService.listaPrdocutos());
            return "pedidos/crear";
        }

        pedidoService.guardar(pedido);
        log.info("Pedido creado exitosamente para: {}", pedido.getNombreCliente());
        return "redirect:/pedidos/historial";
    }

    @GetMapping("/historial")
    public String historial(Model model) {
        model.addAttribute("pedidos", pedidoService.listarPedidos());
        return "pedidos/historial";
    }
}