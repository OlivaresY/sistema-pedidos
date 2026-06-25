package com.panaderia_tu_pan.sistema_pedidos.controller;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import com.panaderia_tu_pan.sistema_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //listar todos los pedidos
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    //crear un nuevo pedido con validación
    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody Pedido pedido) {
        // @Valid asegura que los datos del 'pedido' cumplan con las reglas de Pedido.java
        Pedido nuevoPedido = pedidoService.guardar(pedido);

        // Retornamos 201 Created junto con el objeto guardado
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    //cambiar el estado de un pedido
    @PatchMapping("/{id}/avanzar")
    public ResponseEntity<Void> avanzarEstado(@PathVariable Long id) {
        pedidoService.avanzarEstado(id);
        return ResponseEntity.noContent().build();
    }
}