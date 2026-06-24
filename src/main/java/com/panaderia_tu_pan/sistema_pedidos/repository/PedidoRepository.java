package com.panaderia_tu_pan.sistema_pedidos.repository;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PedidoRepository {

    // Lista donde se almacenarán los pedidos
    private final List<Pedido> listaPedidos = new ArrayList<>();

    //Simula un ID autoincremental
    private Long idContador = 1L;

    //Obtener todos los pedidos
    public List<Pedido> obtenerTodos() {
        return listaPedidos;
    }

    //Guardar un pedido
    public void guardar(Pedido pedido) {
        pedido.setId(idContador);
        listaPedidos.add(pedido);
        idContador++;
    }

    //Buscar pedido por ID
    public Pedido buscarPorId(Long id) {
        return listaPedidos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
