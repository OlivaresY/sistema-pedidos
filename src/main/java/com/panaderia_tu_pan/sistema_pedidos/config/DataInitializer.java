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
        if (productoRepository.count() == 0) {
            List<Producto> productos = List.of(
                    // --- producto inicial ---
                    new Producto(null, "Baguette Tradicional", "Pan francés crujiente", 1200.0, true),
                    new Producto(null, "Croissant de Mantequilla", "Hojaldrado y suave", 1500.0, true),
                    new Producto(null, "Empanada de Carne", "Rellena de carne seleccionada", 1300.0, true),
                    new Producto(null, "Danesa de Frutas", "Pastelito con mermelada artesanal", 1100.0, true),
                    new Producto(null, "Pan de Chocolate", "Hojaldre relleno de chocolate", 1400.0, true),
                    new Producto(null, "Muffin de Vainilla", "Esponjoso y recién horneado", 900.0, true),
                    new Producto(null, "Galleta de Avena", "Con pasas y miel", 700.0, true),
                    new Producto(null, "Torta de Tres Leches", "Postre clásico empapado", 2500.0, true),
                    new Producto(null, "Donas Glaseadas", "Suaves con glaseado real", 850.0, true),
                    new Producto(null, "Pan de Ajo", "Con finas hierbas", 1000.0, true),

                    // --- bebidas calientes ---
                    new Producto(null, "Café Negro", "Recién chorreado", 800.0, true),
                    new Producto(null, "Capuccino", "Con espuma cremosa", 2000.0, true),
                    new Producto(null, "Chocolate Caliente", "Hecho con leche entera", 1800.0, true),

                    // --- bebidas frias ---
                    new Producto(null, "Té Frío Limón", "Natural y refrescante", 1200.0, true),
                    new Producto(null, "Batido de Fresa", "Con leche fresca", 2200.0, true)
            );

            productoRepository.saveAll(productos);
            System.out.println("--- Catálogo completo cargado exitosamente ---");
        }
    }
}