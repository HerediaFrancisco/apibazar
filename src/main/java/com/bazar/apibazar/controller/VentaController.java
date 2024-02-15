package com.bazar.apibazar.controller;

import com.bazar.apibazar.DTO.ventaDTO;
import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.entity.Venta;
import com.bazar.apibazar.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private IVentaService iVentaService;

    @PostMapping("/crear")
    public String crearVenta(@RequestBody Venta venta){
        iVentaService.crearVenta(venta);
        return "Venta creado";
    }

    @GetMapping("/")
    public List<Venta> listarVentas(){
        List<Venta> listarVentas = iVentaService.listarVentas();
        return listarVentas;
    }

    @GetMapping("/{id}")
    public Venta buscarVenta(@PathVariable Long id){
        return iVentaService.buscarVenta(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id){
        iVentaService.eliminarVenta(id);
        return "Venta eliminada";
    }

    /* DEBO MEJORAR ESTA PARTE DEL CODIGO (ACTUALIZAR)*/

    @PutMapping("/actualizar/{idOriginal}")
    public Venta actualizarVenta(@PathVariable Long idOriginal,
                                     @RequestParam(required = false, name = "codigo_venta") Long nuevoCodigo,
                                     @RequestParam(required = false, name = "fecha_venta") LocalDate nuevaFecha,
                                     @RequestParam(required = false, name = "total") Double nuevoTotal){
        iVentaService.actualizarVenta(idOriginal, nuevoCodigo, nuevaFecha, nuevoTotal);
        Venta venta = iVentaService.buscarVenta(nuevoCodigo);

        return venta;
    }
    @PutMapping("/actualizar")
    public Venta actualizarVenta(@RequestBody Venta venta){
        iVentaService.actualizarVenta(venta);
        return iVentaService.buscarVenta(venta.getCodigo_venta());
    }

    @GetMapping("/productosVenta/{idVenta}")
    public List<?> productosVenta(@PathVariable Long idVenta){
        return iVentaService.productosVenta(idVenta);
    }

    @GetMapping("/ventasFecha/{fechaVenta}")
    public String ventasXFecha(@PathVariable LocalDate fechaVenta){
        return iVentaService.montoTotal(fechaVenta);
    }

    @GetMapping("/ventaMayor")
    public ventaDTO ventaMayor(){
        return iVentaService.mayorVenta();
    }
}
