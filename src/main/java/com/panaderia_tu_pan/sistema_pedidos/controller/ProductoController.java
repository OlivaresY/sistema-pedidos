package com.panaderia_tu_pan.sistema_pedidos.controller;

import com.panaderia_tu_pan.sistema_pedidos.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Le dice a spring que esta clase resolvera rutas URÑ web y devolvera vistas HTML
@Controller
public class ProductoController {

    //Inyectamos el Service
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    //end point
    @GetMapping("/productos/menu")
    public String verMenu(Model model){

        //Pedimos los productos al Service
        var lista = productoService.listaPrdocutos();

        //Inyectamos la lista en el Model
        model.addAttribute("productos", lista);

        //Devolvemos el nombre dela plantilla HTML
        return "productos/menu";
    }
}
