package com.bazar.apibazar.controller;

import com.bazar.apibazar.dto.ventaDTO;
import com.bazar.apibazar.entity.Venta;
import com.bazar.apibazar.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private IVentaService iVentaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearVenta(@RequestBody Venta venta){
        iVentaService.crearVenta(venta);
        return new ResponseEntity<>("Venta creado", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Venta>> listarVentas(){
        List<Venta> listarVentas = iVentaService.listarVentas();
        return new ResponseEntity<>(listarVentas,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarVenta(@PathVariable Long id){
        return new ResponseEntity<>(iVentaService.buscarVenta(id),HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id){
        iVentaService.eliminarVenta(id);
        return new ResponseEntity<>("Venta eliminada",HttpStatus.OK);
    }

    /* DEBO MEJORAR ESTA PARTE DEL CODIGO (ACTUALIZAR)*/

    @PutMapping("/actualizar/{idOriginal}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable Long idOriginal,
                                     @RequestParam(required = false, name = "codigo_venta") Long nuevoCodigo,
                                     @RequestParam(required = false, name = "fecha_venta") LocalDate nuevaFecha,
                                     @RequestParam(required = false, name = "total") Double nuevoTotal){
        iVentaService.actualizarVenta(idOriginal, nuevoCodigo, nuevaFecha, nuevoTotal);
        Venta venta = iVentaService.buscarVenta(nuevoCodigo);

        return new ResponseEntity<>(venta,HttpStatus.OK);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Venta> actualizarVenta(@RequestBody Venta venta){
        iVentaService.actualizarVenta(venta);
        return new ResponseEntity<>(iVentaService.buscarVenta(venta.getCodigo_venta()),HttpStatus.OK);
    }

    @GetMapping("/productosVenta/{idVenta}")
    public ResponseEntity<List<?>> productosVenta(@PathVariable Long idVenta){
        return new ResponseEntity<>(iVentaService.productosVenta(idVenta),HttpStatus.OK);
    }

    @GetMapping("/ventasFecha/{fechaVenta}")
    public ResponseEntity<String> ventasXFecha(@PathVariable LocalDate fechaVenta){
        return new ResponseEntity<>(iVentaService.montoTotal(fechaVenta),HttpStatus.OK);
    }

    @GetMapping("/ventaMayor")
    public ResponseEntity<ventaDTO> ventaMayor(){
        return new ResponseEntity<>(iVentaService.mayorVenta(),HttpStatus.OK);
    }
}
