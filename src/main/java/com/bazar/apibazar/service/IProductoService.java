package com.bazar.apibazar.service;

import com.bazar.apibazar.dto.entrada.ProductoEntradaDto;
import com.bazar.apibazar.dto.modificacion.ProductoModifcadoDto;
import com.bazar.apibazar.dto.salida.ProductoSalidaDto;
import com.bazar.apibazar.entity.Producto;


import java.util.List;

public interface IProductoService {

    public ProductoSalidaDto crearProducto(ProductoEntradaDto producto);
    public List<ProductoSalidaDto> listarProductos();
    public ProductoSalidaDto buscarProducto(Long id_producto);
    public void eliminarProducto(Long id_producto);
    public ProductoSalidaDto actualizarProducto(ProductoModifcadoDto producto);

    public List<Producto> stockProductos();

}
