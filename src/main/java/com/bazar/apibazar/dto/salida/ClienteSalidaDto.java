package com.bazar.apibazar.dto.salida;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteSalidaDto {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;

    public ClienteSalidaDto() {
    }
    public ClienteSalidaDto(Long id, String nombre, String apellido, Integer dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
}
