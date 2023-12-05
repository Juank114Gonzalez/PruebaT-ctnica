package com.example.demo.services.impl;

import com.example.demo.model.DetalleFactura;
import com.example.demo.repositories.DetalleFacturaRepository;
import com.example.demo.services.IDetalleFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaServiceImpl implements IDetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    @Override
    public Optional<DetalleFactura> getDetalleFacturaById(Long numDetalle) {
        return detalleFacturaRepository.findById(numDetalle);
    }

    @Override
    public DetalleFactura save(DetalleFactura detalleFactura) {
        return detalleFacturaRepository.save(detalleFactura);
    }

    @Override
    public List<DetalleFactura> getAllDetallesFactura() {
        return detalleFacturaRepository.findAll();
    }

    @Override
    public void deleteDetalleFactura(Long numDetalle) {
        detalleFacturaRepository.deleteById(numDetalle);
    }

    @Override
    public List<DetalleFactura> getDetallesFacturaByIdFactura(Long idFactura) {
        return detalleFacturaRepository.findByFactura_Id(idFactura);
    }

}

