package com.bazar.apibazar.service;

import com.bazar.apibazar.entity.Producto;

import java.util.List;

public interface IProductoService {

    public void crearProducto(Producto producto);
    public List<Producto> listarProductos();
    public Producto buscarProducto(Long id_producto);
    public void eliminarProducto(Long id_producto);
    public void actualizarProducto(Long idOriginal, Long nuevoCodigo,String nuevoNombre,String nuevaMarca,Double nuevoCosto,Integer nuevaCantidad);
    public void actualizarProcuto(Producto producto);
    public List<Producto> stockProductos();
}
