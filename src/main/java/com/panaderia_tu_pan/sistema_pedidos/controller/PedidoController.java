package com.panaderia_tu_pan.sistema_pedidos.controller;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import com.panaderia_tu_pan.sistema_pedidos.model.Producto;
import com.panaderia_tu_pan.sistema_pedidos.service.PedidoService;
import com.panaderia_tu_pan.sistema_pedidos.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public PedidoController(PedidoService pedidoService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model, Authentication authentication) {
        Pedido pedido = new Pedido();
        boolean isAdmin = esAdmin(authentication);
        if (!isAdmin) pedido.setNombreCliente(authentication.getName());

        model.addAttribute("pedido", pedido);
        model.addAttribute("productos", productoService.listaPrdocutos());
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("username", authentication.getName());
        return "pedidos/crear";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@Valid @ModelAttribute Pedido pedido,
                                @RequestParam(value = "productoId", required = false) Long productoId,
                                BindingResult result, Authentication authentication, Model model) {

        boolean isAdmin = esAdmin(authentication);
        if (!isAdmin) pedido.setNombreCliente(authentication.getName());

        //validamos el producto manualmente
        if (productoId == null) {
            model.addAttribute("errorProducto", "Por favor, seleccione un producto.");
        }

        //si el nombre tiene errores o no se eligió producto, volvemos al formulario
        if (result.hasErrors() || productoId == null) {
            model.addAttribute("productos", productoService.listaPrdocutos());
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("username", authentication.getName());
            return "pedidos/crear";
        }

        Producto producto = productoService.buscarPorId(productoId);
        pedido.setProducto(producto);
        pedido.setTotal(producto.getPrecio());
        pedido.setEstado("NUEVO");

        pedidoService.guardar(pedido);
        return "redirect:/pedidos/historial";
    }

    @GetMapping("/historial")
    public String historial(Model model, Authentication authentication) {
        boolean isAdmin = esAdmin(authentication);
        String username = authentication.getName();
        model.addAttribute("pedidos", pedidoService.listarPedidos(username, isAdmin));
        model.addAttribute("username", username);
        model.addAttribute("isAdmin", isAdmin);
        return "pedidos/historial";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id, Authentication authentication) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) return "redirect:/pedidos/historial";
        boolean isAdmin = esAdmin(authentication);
        if (!isAdmin && !pedido.getNombreCliente().equals(authentication.getName())) return "redirect:/pedidos/historial";
        pedidoService.eliminarPedido(id);
        return "redirect:/pedidos/historial";
    }

    private boolean esAdmin(Authentication authentication) {
        return authentication != null && authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));
    }
}