package com.bazar.apibazar.service.impl;

import com.bazar.apibazar.dto.entrada.ClienteEntradaDto;
import com.bazar.apibazar.dto.modificacion.ClienteModifcadoDto;
import com.bazar.apibazar.dto.salida.ClienteSalidaDto;
import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.repository.IClienteRepository;
import com.bazar.apibazar.service.IClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository iClienteRepository;
    @Autowired
    private ModelMapper modelMapper;


    /*
    @Override
    public void crearCliente(Cliente cliente) {
        iClienteRepository.save(cliente);
    }
     */
    @Override
    public ClienteSalidaDto crearCliente(ClienteEntradaDto cliente) {

        Cliente clienteEntidad = modelMapper.map(cliente, Cliente.class);
        Cliente clienteAPersisitir = iClienteRepository.save(clienteEntidad);
        ClienteSalidaDto clienteSalidaDto = modelMapper.map(clienteAPersisitir, ClienteSalidaDto.class);

        return clienteSalidaDto;
    }

    /*
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> listarClientes = iClienteRepository.findAll();
        return listarClientes;
    }

     */
    @Override
    public List<ClienteSalidaDto> listarClientes() {
        List<ClienteSalidaDto> clienteSalidaDtos = iClienteRepository.findAll()
                .stream().map(cliente -> modelMapper.map(cliente, ClienteSalidaDto.class)).toList();
        return clienteSalidaDtos;
    }


    @Override
    public ClienteSalidaDto buscarCliente(Long id_cliente) {
        Cliente clienteBuscado = iClienteRepository.findById(id_cliente).orElse(null);
        ClienteSalidaDto clienteSalidaDto = modelMapper.map(clienteBuscado, ClienteSalidaDto.class);

        // ACA HAY QUE MANEJAR LAS EXCEPCIONES PARA CUANDO NO SE ENCUENTRE UN CLIENTE

        return clienteSalidaDto;
    }

    @Override
    public void eliminarCliente(Long id_cliente) {
        iClienteRepository.deleteById(id_cliente);

        //MANEJAR EXCEPCIONES POR SI NO SE ENCUENTRA EL CLIENTE A ELIMINAR
    }

    /*
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


     */
    @Override
    public ClienteSalidaDto actualizarCliente(ClienteModifcadoDto cliente){
        Cliente clienteRecibido = modelMapper.map(cliente, Cliente.class);
        Cliente clienteAActualizar = iClienteRepository.findById(clienteRecibido.getId()).orElse(null);

        ClienteSalidaDto clienteSalidaDto = null;

        if(clienteAActualizar  != null){
            clienteAActualizar = clienteRecibido;
            iClienteRepository.save(clienteAActualizar);

            clienteSalidaDto = modelMapper.map(clienteAActualizar, ClienteSalidaDto.class);
        }
        return clienteSalidaDto;
    }

    private void configureMapping(){
    modelMapper.typeMap(ClienteEntradaDto.class, Cliente.class);
    modelMapper.typeMap(Cliente.class, ClienteSalidaDto.class);
    modelMapper.typeMap(ClienteModifcadoDto.class, Cliente.class);
    }
}
