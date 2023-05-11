package com.med.api.medico;

import com.med.api.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*Este es un Patron DTO (Data Transfer Object). Los DTOs son objetos que representan datos
       y no contienen lógica de negocio adicional. Son simples contenedores de datos.   */
public record DatosRegistroMedico(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefono,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion) {

}
