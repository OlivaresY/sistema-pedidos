package com.panaderia_tu_pan.sistema_pedidos.controller;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import com.panaderia_tu_pan.sistema_pedidos.model.Producto;
import com.panaderia_tu_pan.sistema_pedidos.service.PedidoService;
import com.panaderia_tu_pan.sistema_pedidos.service.ProductoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
    public String mostrarFormulario(Model model, Authentication authentication) {
        Pedido pedido = new Pedido();

        //determinar si el usuario logueado es ADMIN
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        //si NO es admin, prellenamos con su nombre de usuario
        if (!isAdmin) {
            pedido.setNombreCliente(authentication.getName());
        }

        model.addAttribute("pedido", pedido);
        model.addAttribute("productos", productoService.listaPrdocutos());
        model.addAttribute("isAdmin", isAdmin); // Para usar en la vista
        return "pedidos/crear";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@Valid @ModelAttribute Pedido pedido, BindingResult result,
                                Authentication authentication, Model model) {

        //seguridad: Si no es admin, forzamos que el pedido sea del usuario logueado
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        if (!isAdmin) {
            pedido.setNombreCliente(authentication.getName());
        }

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
            model.addAttribute("isAdmin", isAdmin);
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

    @PostMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Long id, Authentication authentication) {
        //buscamos el pedido para verificar quién es el dueño
        Pedido pedido = pedidoService.buscarPorId(id); // Asegúrate de tener este método en tu Service

        if (pedido == null) {
            return "redirect:/pedidos/historial?error=noencontrado";
        }

        //verificamos si es ADMIN
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        // si NO es admin Y el nombre del pedido no coincide con el usuario logueado, prohibimos el borrado
        if (!isAdmin && !pedido.getNombreCliente().equals(authentication.getName())) {
            log.warn("Intento de borrado no autorizado por: {}", authentication.getName());
            return "redirect:/pedidos/historial?error=noautorizado";
        }

        //si pasó las validaciones, eliminamos
        pedidoService.eliminarPedido(id);
        log.info("Pedido con ID {} eliminado por: {}", id, authentication.getName());

        return "redirect:/pedidos/historial";
    }
}