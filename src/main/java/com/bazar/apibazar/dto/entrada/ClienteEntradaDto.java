package com.bazar.apibazar.dto.entrada;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class ClienteEntradaDto {

    @NotNull(message = "El nombre del Cliente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del Cliente")
    @Size(max = 50, message = "El nombre no debe tener mas de 50 caracteres")
    private String nombre;

    @NotNull(message = "El apellido del Cliente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del Cliente")
    @Size(max = 50, message = "El apellido no debe tener mas de 50 caracteres")
    private String apellido;

    @NotNull(message = "El dni del Cliente no puede ser nulo")
    @Size(max = 20, message = "El dni no debe tener mas de 20 caracteres")
    private Integer dni;

    public ClienteEntradaDto() {
    }

    public ClienteEntradaDto(String nombre, String apellido, Integer dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
}
