package com.panaderia_tu_pan.sistema_pedidos.service;

import com.panaderia_tu_pan.sistema_pedidos.model.Producto;
import com.panaderia_tu_pan.sistema_pedidos.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

//Le decimos a sprint que aquí va la logica comercial
@Service
public class ProductoService {

    //Inyeccion de dependencias -> Service necesita al Repository para funcionar
    private final ProductoRepository productoRepository;

    //El Repository es inyectado  automaticamente al ponerse en el constructor
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    //Regla de negocio simple: Traer la lista completa tal cual nos la da el repository
    public List<Producto> listaPrdocutos(){
        return productoRepository.obtenerTodos();
    }
}
