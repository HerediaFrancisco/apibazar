package com.bazar.apibazar.service;

import com.bazar.apibazar.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public void crearCliente(Cliente cliente);
    public List<Cliente> listarClientes();
    public Cliente buscarCliente(Long id_cliente);
    public void eliminarCliente(Long id_cliente);

    public void actualizarCliente(Long idOriginal, Long nuevoId, String nuevoNombre, String nuevoApellido, Integer nuevoDni);
    public void actualizarCliente(Cliente cliente);

}
