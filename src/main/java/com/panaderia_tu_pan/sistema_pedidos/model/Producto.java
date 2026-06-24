package com.panaderia_tu_pan.sistema_pedidos.model;

import lombok.*;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity //indica que esto es una tabla de BD
@Table(name = "productos") //nombre de la tabla

public class Producto{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto incremental
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private boolean disponible;
}

