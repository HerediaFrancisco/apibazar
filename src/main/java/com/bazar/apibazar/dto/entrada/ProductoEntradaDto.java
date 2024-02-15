package com.bazar.apibazar.dto.entrada;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductoEntradaDto {

    @NotNull(message = "El nombre del Producto no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del Producto")
    @Size(max = 100, message = "El nombre no debe tener mas de 100 caracteres")
    private String nombre;
    @NotNull(message = "La marca del Producto no puede ser nulo")
    @NotBlank(message = "Debe especificarse la marca del Producto")
    @Size(max = 50, message = "La marca no debe tener mas de 50 caracteres")
    private String marca;
    @NotNull(message = "El costo del Producto no puede ser nulo")
    private Double costo;
    @NotNull(message = "La cantidad disponible del Producto no puede ser nulo")
    private Integer cantidad_disponible;

    public ProductoEntradaDto() {
    }

    public ProductoEntradaDto(String nombre, String marca, Double costo, Integer cantidad_disponible) {
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }
}
