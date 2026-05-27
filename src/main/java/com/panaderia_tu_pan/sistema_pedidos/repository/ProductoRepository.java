package com.panaderia_tu_pan.sistema_pedidos.repository;

import com.panaderia_tu_pan.sistema_pedidos.model.Producto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository

public class ProductoRepository {

    //mi base de productos en quemado
    private final List<Producto> listaProductos = new ArrayList<>();

    public ProductoRepository() {
        listaProductos.add(new Producto(1L, "Baguette Tradicional", "Pan frances crujiente recien horneado", 1200.0, true));
        listaProductos.add(new Producto(2L, "Croissant de Mantequilla", "Hojaldrado suave y calientito", 1500.0, true));
        listaProductos.add(new Producto(4L, "Empanada", "Rellena de Queso, Carne ó Pollo", 1300.0, true));
        listaProductos.add(new Producto(5L,"Cafe Negro", "Cafe recien chorreado en su mesa", 800.0, true));
        listaProductos.add(new Producto(6L, "Capuccino", "Cafe equilibrio perfecto entre espresso, leche vaporizada y espuma", 2000.0, true));
    }

    //Metodo que permite las otras capas leer los productos
    public List<Producto> obtenerTodos(){
        return listaProductos;
    }

    // Metodo para buscar un producto por ID
    public Producto buscarPorId(Long id) {
        return listaProductos.stream()
                .filter(p->p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
