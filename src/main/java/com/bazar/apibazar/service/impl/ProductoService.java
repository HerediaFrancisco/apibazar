package com.bazar.apibazar.service.impl;

import com.bazar.apibazar.dto.entrada.ClienteEntradaDto;
import com.bazar.apibazar.dto.entrada.ProductoEntradaDto;
import com.bazar.apibazar.dto.modificacion.ClienteModifcadoDto;
import com.bazar.apibazar.dto.modificacion.ProductoModifcadoDto;
import com.bazar.apibazar.dto.salida.ClienteSalidaDto;
import com.bazar.apibazar.dto.salida.ProductoSalidaDto;
import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.entity.Producto;
import com.bazar.apibazar.repository.IProductoRepository;
import com.bazar.apibazar.service.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository iProductoRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductoSalidaDto crearProducto(ProductoEntradaDto producto) {

        Producto productoEntidad = modelMapper.map(producto, Producto.class);
        Producto productoAPersistir = iProductoRepository.save(productoEntidad);
        ProductoSalidaDto productoSalidaDto = modelMapper.map(productoAPersistir,ProductoSalidaDto.class);

        return productoSalidaDto;
    }

    @Override
    public List<ProductoSalidaDto> listarProductos() {
        List<ProductoSalidaDto> productoSalidaDtos = iProductoRepository.findAll()
                .stream().map(producto -> modelMapper.map(producto, ProductoSalidaDto.class)).toList();
        return productoSalidaDtos;
    }

    @Override
    public ProductoSalidaDto buscarProducto(Long id_producto) {
        Producto productoBuscado = iProductoRepository.findById(id_producto).orElse(null);
        ProductoSalidaDto productoSalidaDto = modelMapper.map(productoBuscado, ProductoSalidaDto.class);

        // ACA HAY QUE MANEJAR LAS EXCEPCIONES PARA CUANDO NO SE ENCUENTRE UN PRODUCTO

        return productoSalidaDto;
    }

    @Override
    public void eliminarProducto(Long id_producto) {
        iProductoRepository.deleteById(id_producto);
        //MANEJAR EXCEPCIONES POR SI NO SE ENCUENTRA EL CLIENTE A ELIMINAR
    }

    @Override
    public ProductoSalidaDto actualizarProducto(ProductoModifcadoDto producto){
        Producto productoRecibido = modelMapper.map(producto, Producto.class);
        Producto productoAActualizar = iProductoRepository.findById(productoRecibido.getCodigo_producto()).orElse(null);

        ProductoSalidaDto productoSalidaDto = null;

        if(productoAActualizar != null){
            productoAActualizar = productoRecibido;
            iProductoRepository.save(productoAActualizar);

            productoSalidaDto = modelMapper.map(productoAActualizar, ProductoSalidaDto.class);
        }

        return productoSalidaDto;
    }
/*
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

 */

@Override
public List<Producto> stockProductos() {
    List<ProductoSalidaDto> productoSalidaDtos = listarProductos();

    List<Producto> stock = productoSalidaDtos.stream()
            .map(productoDto -> modelMapper.map(productoDto, Producto.class)).toList();

    List<Producto> faltaStock = stock.stream()
            .filter(producto -> producto.getCantidad_disponible() < 5).toList();

    return faltaStock;

}

    private void configureMapping(){
        modelMapper.typeMap(ProductoEntradaDto.class, Producto.class);
        modelMapper.typeMap(Producto.class, ProductoSalidaDto.class);
        modelMapper.typeMap(ProductoModifcadoDto.class, Producto.class);
    }
}
