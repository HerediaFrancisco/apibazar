package com.bazar.apibazar.controller;

import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.entity.Producto;
import com.bazar.apibazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProdcutoController {

    @Autowired
    private IProductoService iProductoService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCProducto(@RequestBody Producto producto){
        iProductoService.crearProducto(producto);
        return new ResponseEntity<>("Producto creado", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> listarProductos = iProductoService.listarProductos();
        return new ResponseEntity<>(listarProductos,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable Long id){
        return new ResponseEntity<>(iProductoService.buscarProducto(id),HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        iProductoService.eliminarProducto(id);
        return new ResponseEntity<>("Producto eliminado",HttpStatus.OK);
    }

    @PutMapping("/actualizar/{idOriginal}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long idOriginal,
                                     @RequestParam(required = false, name = "codigo_producto") Long nuevoCodigo,
                                     @RequestParam(required = false, name = "nombre") String nuevoNombre,
                                     @RequestParam(required = false, name = "marca") String nuevaMarca,
                                     @RequestParam(required = false, name = "costo") Double nuevoCosto,
                                     @RequestParam(required = false, name = "cantidad_disponible") Integer nuevaCantidad){
        iProductoService.actualizarProducto(idOriginal,nuevoCodigo,nuevoNombre,nuevaMarca,nuevoCosto,nuevaCantidad);
        Producto producto = iProductoService.buscarProducto(nuevoCodigo);
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }


    @PutMapping("/actualizar")
    public ResponseEntity<Producto> actualizarProdcuto(@RequestBody Producto producto){
        iProductoService.actualizarProcuto(producto);
        return new ResponseEntity<>(iProductoService.buscarProducto(producto.getCodigo_producto()),HttpStatus.OK);
    }

    @GetMapping("/faltaStock")
    public ResponseEntity<List<Producto>> faltaStock(){
        List<Producto> faltaStock = iProductoService.stockProductos();
        return new ResponseEntity<>(faltaStock,HttpStatus.OK);
    }
}
