package com.bazar.apibazar.dto.salida;

import com.bazar.apibazar.entity.Cliente;
import com.bazar.apibazar.entity.Producto;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class VentaSalidaDto {

    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;
    private List<Producto> listaProductos;
    private ClienteSalidaDto clienteSalidaDto;

    public VentaSalidaDto() {
    }

    public VentaSalidaDto(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, ClienteSalidaDto clienteSalidaDto) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.clienteSalidaDto = clienteSalidaDto;
    }
}
