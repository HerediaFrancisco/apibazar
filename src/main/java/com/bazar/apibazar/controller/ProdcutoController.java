package com.bazar.apibazar.controller;

import com.bazar.apibazar.dto.entrada.ProductoEntradaDto;
import com.bazar.apibazar.dto.modificacion.ProductoModifcadoDto;
import com.bazar.apibazar.dto.salida.ProductoSalidaDto;
import com.bazar.apibazar.entity.Producto;
import com.bazar.apibazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProdcutoController {

    @Autowired
    private IProductoService iProductoService;

    @PostMapping("/crear")
    public ResponseEntity<ProductoSalidaDto> crearProducto(@RequestBody @Valid ProductoEntradaDto producto){
        return new ResponseEntity<>(iProductoService.crearProducto(producto), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductoSalidaDto>> listarProductos(){
        List<ProductoSalidaDto> productoSalidaDtos = iProductoService.listarProductos();
        return new ResponseEntity<>(productoSalidaDtos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoSalidaDto> buscarProducto(@PathVariable Long id){
        return new ResponseEntity<>(iProductoService.buscarProducto(id),HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        iProductoService.eliminarProducto(id);
        return new ResponseEntity<>("Producto eliminado",HttpStatus.OK);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<ProductoSalidaDto> actualizarProdcuto(@RequestBody @Valid ProductoModifcadoDto producto){

        return new ResponseEntity<>(iProductoService.actualizarProducto(producto),HttpStatus.OK);
    }

    @GetMapping("/faltaStock")
    public ResponseEntity<List<Producto>> faltaStock(){
        List<Producto> faltaStock = iProductoService.stockProductos();
        return new ResponseEntity<>(faltaStock,HttpStatus.OK);
    }


}
