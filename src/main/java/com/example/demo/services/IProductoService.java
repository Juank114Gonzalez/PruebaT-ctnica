package com.example.demo.services;

import com.example.demo.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    Optional<Producto> getProductoById(Long id);

    Producto save(Producto producto);

    List<Producto> getAllProductos();

    void deleteProducto(Long id);

    // Otros métodos específicos si es necesario
}