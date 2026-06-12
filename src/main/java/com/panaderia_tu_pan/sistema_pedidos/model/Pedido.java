package com.panaderia_tu_pan.sistema_pedidos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private Long id;
    private String nombreCliente;
    private Producto producto;
    private double total;
    private String estado;
}