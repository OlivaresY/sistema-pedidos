package com.panaderia_tu_pan.sistema_pedidos.repository;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    //listar los pedidos de un usuario específico
    //consulta SQL automáticamente basándose en el nombre del método
    List<Pedido> findByNombreCliente(String nombreCliente);

}