package com.bazar.apibazar.controller;

import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente){
        iClienteService.crearCliente(cliente);
        return new ResponseEntity<>("Cliente creado", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> listarClientes(){
        List<Cliente> listarClientes = iClienteService.listarClientes();
        return new ResponseEntity<>(listarClientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id){
        return new ResponseEntity<>(iClienteService.buscarCliente(id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id){
        iClienteService.eliminarCliente(id);
        return new ResponseEntity<>("Cliente eliminado", HttpStatus.OK);
    }

    @PutMapping("/actualizar/{idOriginal}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long idOriginal,
                                     @RequestParam(required = false, name = "id") Long nuevoId,
                                     @RequestParam(required = false, name = "nombre") String nuevoNombre,
                                     @RequestParam(required = false, name = "apellido") String nuevoApellido,
                                     @RequestParam(required = false, name = "dni") Integer nuevoDni) {
        iClienteService.actualizarCliente(idOriginal, nuevoId, nuevoNombre, nuevoApellido, nuevoDni);
        Cliente cliente = iClienteService.buscarCliente(nuevoId);

        return new ResponseEntity<>(cliente,HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Cliente> actualizarCliente(@RequestBody Cliente cliente){
        iClienteService.actualizarCliente(cliente);
        return new ResponseEntity<>(iClienteService.buscarCliente(cliente.getId()),HttpStatus.OK);
    }
}
