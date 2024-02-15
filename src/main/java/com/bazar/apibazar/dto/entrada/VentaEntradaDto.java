package com.bazar.apibazar.dto.entrada;

import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.entity.Producto;
import lombok.Getter;
import lombok.Setter;


import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VentaEntradaDto {

    @NotNull(message = "Debe especificarse la fecha de venta")
    @FutureOrPresent(message = "La fecha debe ser la fecha actual o una fecha futura")
    private LocalDate fecha_venta;
    @NotNull(message = "El total de la venta no puede ser nula")
    private Double total;
    @NotEmpty(message = "La lista no puede estar vacía")
    @Valid
    private List<Producto> listaProductos;
    @NotNull(message = "El Cliente no puede ser nulo para una venta")
    @Valid
    private ClienteEntradaDto clienteEntradaDto;

    public VentaEntradaDto() {
    }

    public VentaEntradaDto(LocalDate fecha_venta, Double total, List<Producto> listaProductos, ClienteEntradaDto clienteEntradaDto) {
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.clienteEntradaDto = clienteEntradaDto;
    }
}
