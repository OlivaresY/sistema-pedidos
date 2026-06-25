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
        return pedidoRepository.findAll();
    }

    //cambiamos el nombre y el tipo de retorno
    public Pedido guardar(Pedido pedido) {
        pedido.setEstado("NUEVO");
        return pedidoRepository.save(pedido); //retornamos el objeto guardado
    }

    public void avanzarEstado(Long idPedido) {
        pedidoRepository.findById(idPedido).ifPresent(pedido -> {
            if ("NUEVO".equals(pedido.getEstado())) {
                pedido.setEstado("EN_PREPARACION");
            } else if ("EN_PREPARACION".equals(pedido.getEstado())) {
                pedido.setEstado("ENTREGADO");
            }
            pedidoRepository.save(pedido);
        });
    }
}