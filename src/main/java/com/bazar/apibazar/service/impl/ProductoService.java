package com.bazar.apibazar.service.impl;

import com.bazar.apibazar.entity.Producto;
import com.bazar.apibazar.repository.IProductoRepository;
import com.bazar.apibazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository iProductoRepository;
    @Override
    public void crearProducto(Producto producto) {
        iProductoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        List<Producto> listarProductos = iProductoRepository.findAll();
        return listarProductos;
    }

    @Override
    public Producto buscarProducto(Long id_producto) {
        Producto producto = iProductoRepository.findById(id_producto).orElse(null);
        return producto;
    }

    @Override
    public void eliminarProducto(Long id_producto) {
        iProductoRepository.deleteById(id_producto);
    }

    @Override
    public void actualizarProducto(Long idOriginal, Long nuevoCodigo, String nuevoNombre, String nuevaMarca, Double nuevoCosto, Integer nuevaCantidad) {
        Producto producto = this.buscarProducto(idOriginal);
        producto.setCodigo_producto(nuevoCodigo);
        producto.setNombre(nuevoNombre);
        producto.setMarca(nuevaMarca);
        producto.setCosto(nuevoCosto);
        producto.setCantidad_disponible(nuevaCantidad);

        this.crearProducto(producto);
    }

    @Override
    public void actualizarProcuto(Producto producto) {
        this.crearProducto(producto);
    }

    @Override
    public List<Producto> stockProductos() {
        List<Producto> stock = this.listarProductos();
        List<Producto> faltaStock = new ArrayList<>();

        for (Producto producto : stock) {
            if(producto.getCantidad_disponible() < 5){
                faltaStock.add(producto);
            }
        }
        return faltaStock;
    }
}
