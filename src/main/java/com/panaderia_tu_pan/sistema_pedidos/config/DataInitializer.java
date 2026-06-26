package com.panaderia_tu_pan.sistema_pedidos.config;

import com.panaderia_tu_pan.sistema_pedidos.model.Producto;
import com.panaderia_tu_pan.sistema_pedidos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        // inserta si la tabla de productos está vacía
        if (productoRepository.count() == 0) {
            List<Producto> productos = List.of(
                    new Producto(null, "Baguette Tradicional", "Pan francés crujiente recién horneado", 1200.0, true),
                    new Producto(null, "Croissant de Mantequilla", "Hojaldrado suave y calientito", 1500.0, true),
                    new Producto(null, "Empanada", "Rellena de Queso, Carne ó Pollo", 1300.0, true)
            );

            productoRepository.saveAll(productos);
            System.out.println("--- Productos iniciales cargados en la base de datos ---");
        }
    }
}