package com.example.demo.config;


import com.example.demo.model.Cliente;
import com.example.demo.model.Producto;
import com.example.demo.services.impl.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.example.demo.services.impl.ClienteServiceImpl;

import java.sql.Date;

@Component
public class CargaInicialDatos implements ApplicationRunner {

    @Autowired
    private ClienteServiceImpl clienteService;

    @Autowired
    private ProductoServiceImpl productoService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Cargar datos iniciales para clientes
        Cliente cliente1 = new Cliente(1L, "Juan", "Pérez", "Dirección 1", new Date(System.currentTimeMillis()), "123456789", "juan@email.com", "Categoria A");
        Cliente cliente2 = new Cliente(2L, "María", "López", "Dirección 2", new Date(System.currentTimeMillis()), "987654321", "maria@email.com", "Categoria B");

        clienteService.save(cliente1);
        clienteService.save(cliente2);

        // Cargar datos iniciales para productos
        Producto producto1 = new Producto(1L,"Celular", 500000.0, 100L);
        Producto producto2 = new Producto(2L, "Computador", 700000.0, 50L);

        productoService.save(producto1);
        productoService.save(producto2);
    }

}