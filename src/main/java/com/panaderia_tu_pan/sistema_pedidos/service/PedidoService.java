package com.panaderia_tu_pan.sistema_pedidos.service;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import com.panaderia_tu_pan.sistema_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.obtenerTodos();
    }

    //Crear pedido
    public void crearPedido(Pedido pedido) {

        pedido.setTotal(
                pedido.getProducto().getPrecio()
        );

        pedido.setEstado("NUEVO");

        pedidoRepository.guardar(pedido);
    }

    //Avanzar estado
    public void avanzarEstado(Long idPedido) {

        Pedido pedido =
                pedidoRepository.buscarPorId(idPedido);

        if (pedido != null) {

            if (pedido.getEstado().equals("NUEVO")) {

                pedido.setEstado("EN_PREPARACION");

            } else if (pedido.getEstado().equals("EN_PREPARACION")) {

                pedido.setEstado("ENTREGADO");

            }
        }
    }
}
