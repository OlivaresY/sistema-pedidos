package com.panaderia_tu_pan.sistema_pedidos.repository;

import com.panaderia_tu_pan.sistema_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> { //JpaRepository  trae .findAll(), .save(), .findById()
}