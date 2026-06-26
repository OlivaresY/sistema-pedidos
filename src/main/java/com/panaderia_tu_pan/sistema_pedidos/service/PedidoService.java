package com.panaderia_tu_pan.sistema_pedidos.service;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import com.panaderia_tu_pan.sistema_pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // --- ESTE ES EL MÉTODO QUE TE FALTA ---
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.saveAndFlush(pedido);
    }

    // --- Y ESTE PARA ELIMINAR ---
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    public void avanzarEstado(Long idPedido) {
        pedidoRepository.findById(idPedido).ifPresent(pedido -> {
            if ("NUEVO".equals(pedido.getEstado())) {
                pedido.setEstado("EN_PREPARACION");
            } else if ("EN_PREPARACION".equals(pedido.getEstado())) {
                pedido.setEstado("ENTREGADO");
            }
            pedidoRepository.saveAndFlush(pedido);
        });
    }
}