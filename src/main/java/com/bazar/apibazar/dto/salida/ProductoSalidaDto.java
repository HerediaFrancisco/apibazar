package com.bazar.apibazar.dto.salida;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoSalidaDto {

    private Long codigo_producto;
    private String nombre;
    private String marca;
    private Double costo;
    private Integer cantidad_disponible;

    public ProductoSalidaDto() {
    }

    public ProductoSalidaDto(Long codigo_producto, String nombre, String marca, Double costo, Integer cantidad_disponible) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }
}
