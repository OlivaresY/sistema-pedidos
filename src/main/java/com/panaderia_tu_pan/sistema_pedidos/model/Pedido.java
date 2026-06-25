package com.panaderia_tu_pan.sistema_pedidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombreCliente;

    @NotNull(message = "Debe seleccionar un producto")
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0")
    private double total;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;
}