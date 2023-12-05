package com.example.demo.services;

import com.example.demo.model.Factura;

import java.util.List;
import java.util.Optional;

public interface IFacturaService {
    Optional<Factura> getFacturaById(Long id);

    Factura save(Factura note);

    List<Factura> getAllFacturas();

    void deleteFactura(Long id);
}
