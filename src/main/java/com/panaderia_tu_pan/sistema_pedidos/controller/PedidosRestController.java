package com.panaderia_tu_pan.sistema_pedidos.controller;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import com.panaderia_tu_pan.sistema_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRestController {

    private final PedidoService pedidoService;

    // Inyección por constructor
    public PedidosRestController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    // Listar pedidos filtrando por usuario logueado o mostrando todo si es ADMIN
    @GetMapping
    public List<Pedido> listarPedidos(Authentication authentication) {
        String username = authentication.getName();

        // Verificamos si es ADMIN
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        // Llamamos al servicio con los parámetros requeridos
        return pedidoService.listarPedidos(username, isAdmin);
    }

    // Crear un nuevo pedido con validación
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.guardar(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    // Cambiar el estado de un pedido
    @PatchMapping("/{id}/avanzar")
    public ResponseEntity<Void> avanzarEstado(@PathVariable Long id) {
        pedidoService.avanzarEstado(id);
        return ResponseEntity.noContent().build();
    }
}