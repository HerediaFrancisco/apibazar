package com.bazar.apibazar.service;

import com.bazar.apibazar.dto.entrada.ClienteEntradaDto;
import com.bazar.apibazar.dto.modificacion.ClienteModifcadoDto;
import com.bazar.apibazar.dto.salida.ClienteSalidaDto;
import com.bazar.apibazar.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public ClienteSalidaDto crearCliente(ClienteEntradaDto cliente);
    public List<ClienteSalidaDto> listarClientes();
    public ClienteSalidaDto buscarCliente(Long id_cliente);
    public void eliminarCliente(Long id_cliente);
    public ClienteSalidaDto actualizarCliente(ClienteModifcadoDto cliente);


}
