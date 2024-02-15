package com.bazar.apibazar.controller;

import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.entity.Producto;
import com.bazar.apibazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProdcutoController {

    @Autowired
    private IProductoService iProductoService;

    @PostMapping("/crear")
    public String crearCProducto(@RequestBody Producto producto){
        iProductoService.crearProducto(producto);
        return "Producto creado";
    }

    @GetMapping("/")
    public List<Producto> listarProductos(){
        List<Producto> listarProductos = iProductoService.listarProductos();
        return listarProductos;
    }

    @GetMapping("/{id}")
    public Producto buscarProducto(@PathVariable Long id){
        return iProductoService.buscarProducto(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id){
        iProductoService.eliminarProducto(id);
        return "Producto eliminado";
    }

    @PutMapping("/actualizar/{idOriginal}")
    public Producto actualizarProducto(@PathVariable Long idOriginal,
                                     @RequestParam(required = false, name = "codigo_producto") Long nuevoCodigo,
                                     @RequestParam(required = false, name = "nombre") String nuevoNombre,
                                     @RequestParam(required = false, name = "marca") String nuevaMarca,
                                     @RequestParam(required = false, name = "costo") Double nuevoCosto,
                                     @RequestParam(required = false, name = "cantidad_disponible") Integer nuevaCantidad){
        iProductoService.actualizarProducto(idOriginal,nuevoCodigo,nuevoNombre,nuevaMarca,nuevoCosto,nuevaCantidad);
        Producto producto = iProductoService.buscarProducto(nuevoCodigo);
        return producto;
    }


    @PutMapping("/actualizar")
    public Producto actualizarProdcuto(@RequestBody Producto producto){
        iProductoService.actualizarProcuto(producto);
        return iProductoService.buscarProducto(producto.getCodigo_producto());
    }

    @GetMapping("/faltaStock")
    public List<Producto> faltaStock(){
        List<Producto> faltaStock = iProductoService.stockProductos();
        return faltaStock;
    }
}
