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
        return pedidoRepository.findAll(); //findAll()
    }

    public void crearPedido(Pedido pedido) {
        pedido.setEstado("NUEVO");
        pedidoRepository.save(pedido); //save()
    }

    public void avanzarEstado(Long idPedido) {
        //findById devuelve Optional
        pedidoRepository.findById(idPedido).ifPresent(pedido -> {
            if ("NUEVO".equals(pedido.getEstado())) {
                pedido.setEstado("EN_PREPARACION");
            } else if ("EN_PREPARACION".equals(pedido.getEstado())) {
                pedido.setEstado("ENTREGADO");
            }
            pedidoRepository.save(pedido); //guardar cambios
        });
    }
}