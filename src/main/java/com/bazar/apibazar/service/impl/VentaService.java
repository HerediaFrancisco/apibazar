package com.bazar.apibazar.service.impl;

import com.bazar.apibazar.DTO.ventaDTO;
import com.bazar.apibazar.entity.Producto;
import com.bazar.apibazar.entity.Venta;
import com.bazar.apibazar.repository.IVentaRepository;
import com.bazar.apibazar.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository iVentaRepository;

    @Override
    public void crearVenta(Venta venta) {
        iVentaRepository.save(venta);
    }

    @Override
    public List<Venta> listarVentas() {
        List<Venta> listarVentas = iVentaRepository.findAll();
        return listarVentas;
    }

    @Override
    public Venta buscarVenta(Long id_venta) {
        Venta venta = iVentaRepository.findById(id_venta).orElse(null);
        return venta;
    }

    @Override
    public void eliminarVenta(Long id_venta) {
        iVentaRepository.deleteById(id_venta);
    }

    @Override
    public void actualizarVenta(Long idOriginal, Long nuevoCodigo, LocalDate nuevaFecha, Double nuevoTotal) {
        Venta venta = this.buscarVenta(idOriginal);
        venta.setCodigo_venta(nuevoCodigo);
        venta.setFecha_venta(nuevaFecha);
        venta.setTotal(nuevoTotal);

        this.crearVenta(venta);
    }

    @Override
    public void actualizarVenta(Venta venta) {
        this.crearVenta(venta);
    }

    @Override
    public List<?> productosVenta(Long id_venta) {

        Venta buscarVenta = this.buscarVenta(id_venta);

        List<?> productos = buscarVenta.getListaProductos();
        return productos;
    }

    @Override
    public String montoTotal(LocalDate fechaVenta) {
        List<Venta> listaVenta = this.listarVentas();
        List<Venta> ventaFecha = new ArrayList<>();

        int cantidad = 0;
        int montoTotal = 0;
        for (Venta venta : listaVenta) {
            if(venta.getFecha_venta().equals(fechaVenta)){
                cantidad++;
                montoTotal += venta.getListaProductos().get(0).getCosto();
            }
        }

        return "La cantidad es: " + cantidad + " y el monto total es: " + montoTotal;
    }


    @Override
    public ventaDTO mayorVenta() {
        ventaDTO ventaDto = new ventaDTO();
        List<Venta> ventas = this.listarVentas();

        if (ventas.isEmpty()) {
            return ventaDto;
        }

        Venta ventaMayor = ventas.get(0);
        double totalMayor = ventaMayor.getTotal();
        int cantidadProductos = ventaMayor.getListaProductos().size();

        for (Venta venta : ventas) {
            double totalVenta = venta.getTotal();
            if (totalVenta > totalMayor) {
                ventaMayor = venta;
                totalMayor = totalVenta;
                cantidadProductos = venta.getListaProductos().size();
            }
        }

        ventaDto.setCodigo_venta(ventaMayor.getCodigo_venta());
        ventaDto.setTotal(totalMayor);
        ventaDto.setCantProductos(cantidadProductos);
        ventaDto.setNombre(ventaMayor.getCliente().getNombre());
        ventaDto.setApellido(ventaMayor.getCliente().getApellido());

        return ventaDto;
    }

}
