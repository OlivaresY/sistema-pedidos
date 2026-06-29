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

    //inyección por constructor
    public PedidosRestController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    //listar pedidos filtrando por usuario logueados
    @GetMapping
    public List<Pedido> listarPedidos(Authentication authentication) {
        String username = authentication.getName();

        //verificamos si es ADMIN
        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN"));

        return pedidoService.listarPedidos(username, isAdmin);
    }

    //crear un nuevo pedido con validación
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.guardar(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    //cambiar el estado de un pedido
    @PatchMapping("/{id}/avanzar")
    public ResponseEntity<Void> avanzarEstado(@PathVariable Long id) {
        pedidoService.avanzarEstado(id);
        return ResponseEntity.noContent().build();
    }
}