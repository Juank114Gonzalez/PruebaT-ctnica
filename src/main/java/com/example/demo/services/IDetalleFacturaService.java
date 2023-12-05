package com.example.demo.services;

import com.example.demo.model.DetalleFactura;

import java.util.List;
import java.util.Optional;

public interface IDetalleFacturaService {
    Optional<DetalleFactura> getDetalleFacturaById(Long numDetalle);

    DetalleFactura save(DetalleFactura detalleFactura);

    List<DetalleFactura> getAllDetallesFactura();

    void deleteDetalleFactura(Long numDetalle);

    List<DetalleFactura> getDetallesFacturaByIdFactura(Long idFactura);
}
