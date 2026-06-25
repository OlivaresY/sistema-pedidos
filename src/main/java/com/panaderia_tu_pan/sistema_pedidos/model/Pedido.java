package com.panaderia_tu_pan.sistema_pedidos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos") //crea la tabla en MySQL
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL genere el ID
    private Long id;

    private String nombreCliente;

    @ManyToOne //muchos pedidos pueden tener  muchos productos
    @JoinColumn(name = "producto_id") //FK en tabla pedidos
    private Producto producto;

    private double total;
    private String estado;
}