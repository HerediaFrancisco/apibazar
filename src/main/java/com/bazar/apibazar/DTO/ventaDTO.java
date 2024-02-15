package com.bazar.apibazar.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ventaDTO implements Serializable {

    private Long codigo_venta;
    private Double total;
    private int cantProductos;
    private String nombre;
    private String apellido;

    public ventaDTO() {
    }

    public ventaDTO(Long codigo_venta, Double total, int cantProductos, String nombre, String apellido) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantProductos = cantProductos;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
