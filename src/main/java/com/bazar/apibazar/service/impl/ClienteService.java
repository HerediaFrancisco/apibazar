package com.bazar.apibazar.service.impl;

import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.repository.IClienteRepository;
import com.bazar.apibazar.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;

    @Override
    public void crearCliente(Cliente cliente) {
        iClienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> listarClientes = iClienteRepository.findAll();
        return listarClientes;
    }

    @Override
    public Cliente buscarCliente(Long id_cliente) {
        Cliente cliente = iClienteRepository.findById(id_cliente).orElse(null);
        return cliente;
    }

    @Override
    public void eliminarCliente(Long id_cliente) {
        iClienteRepository.deleteById(id_cliente);
    }

    @Override
    public void actualizarCliente(Long idOriginal, Long nuevoId, String nuevoNombre, String nuevoApellido, Integer nuevoDni) {
        Cliente cliente = this.buscarCliente(idOriginal);
        cliente.setId(nuevoId);
        cliente.setNombre(nuevoNombre);
        cliente.setApellido(nuevoApellido);
        cliente.setDni(nuevoDni);

        this.crearCliente(cliente);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        this.crearCliente(cliente);
    }
}
