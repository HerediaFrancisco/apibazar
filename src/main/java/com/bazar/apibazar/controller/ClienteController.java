package com.bazar.apibazar.controller;

import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping("/crear")
    public String crearCliente(@RequestBody Cliente cliente){
        iClienteService.crearCliente(cliente);
        return "Cliente creado";
    }

    @GetMapping("/")
    public List<Cliente> listarClientes(){
        List<Cliente> listarClientes = iClienteService.listarClientes();
        return listarClientes;
    }

    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable Long id){
        return iClienteService.buscarCliente(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id){
        iClienteService.eliminarCliente(id);
        return "Cliente eliminado";
    }

    @PutMapping("/actualizar/{idOriginal}")
    public Cliente actualizarCliente(@PathVariable Long idOriginal,
                                     @RequestParam(required = false, name = "id") Long nuevoId,
                                     @RequestParam(required = false, name = "nombre") String nuevoNombre,
                                     @RequestParam(required = false, name = "apellido") String nuevoApellido,
                                     @RequestParam(required = false, name = "dni") Integer nuevoDni) {
        iClienteService.actualizarCliente(idOriginal, nuevoId, nuevoNombre, nuevoApellido, nuevoDni);
        Cliente cliente = iClienteService.buscarCliente(nuevoId);
        return cliente;
    }

    @PutMapping("/actualizar")
    public Cliente actualizarCliente(@RequestBody Cliente cliente){
        iClienteService.actualizarCliente(cliente);
        return iClienteService.buscarCliente(cliente.getId());
    }
}
