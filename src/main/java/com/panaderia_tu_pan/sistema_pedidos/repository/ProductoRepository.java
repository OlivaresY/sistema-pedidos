package com.panaderia_tu_pan.sistema_pedidos.repository;

import com.panaderia_tu_pan.sistema_pedidos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {//incluye findAll(), save(), findById(), delete(), etc.
}