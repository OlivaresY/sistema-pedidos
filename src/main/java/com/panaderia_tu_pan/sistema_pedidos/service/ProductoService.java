package com.panaderia_tu_pan.sistema_pedidos.service;

import com.panaderia_tu_pan.sistema_pedidos.model.Producto;
import com.panaderia_tu_pan.sistema_pedidos.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listaPrdocutos() {
        return productoRepository.findAll();
    }

    // Método nuevo añadido para buscar producto por ID de forma segura
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
}