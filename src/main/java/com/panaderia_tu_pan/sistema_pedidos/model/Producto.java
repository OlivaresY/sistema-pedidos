package com.panaderia_tu_pan.sistema_pedidos.model;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponible;
}

