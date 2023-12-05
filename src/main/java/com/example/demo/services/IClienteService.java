package com.example.demo.services;

import com.example.demo.model.Cliente;

import java.util.Optional;
import java.util.List;


public interface IClienteService {
    
    Optional<Cliente> getClienteById(Long id);

    Cliente save(Cliente note);

    List<Cliente> getAllClientes();

    void deleteCliente(Long id);

}
