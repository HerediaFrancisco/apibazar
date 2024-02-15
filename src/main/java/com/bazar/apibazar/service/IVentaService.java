package com.bazar.apibazar.service;

import com.bazar.apibazar.DTO.ventaDTO;
import com.bazar.apibazar.entity.Producto;
import com.bazar.apibazar.entity.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    public void crearVenta(Venta venta);
    public List<Venta> listarVentas();
    public Venta buscarVenta(Long id_venta);
    public void eliminarVenta(Long id_venta);
    public void actualizarVenta(Long idOriginal,Long nuevoCodigo, LocalDate nuevaFecha, Double nuevoTotal);
    public void actualizarVenta(Venta venta);
    public List<?> productosVenta(Long id_venta);
    public String montoTotal(LocalDate fechaVenta);

    public ventaDTO mayorVenta();
}
