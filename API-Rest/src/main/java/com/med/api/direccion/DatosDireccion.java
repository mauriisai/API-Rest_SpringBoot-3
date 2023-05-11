package com.med.api.direccion;

import jakarta.validation.constraints.NotBlank;

/* Este es un Patron DTO(Data Transfer Object) es un patrón de diseño que se utiliza comúnmente en el desarrollo de software
   para transferir datos entre diferentes componentes de una aplicación. El objetivo principal del patrón DTO es
   encapsular y transportar datos de manera eficiente entre diferentes capas o servicios de una aplicación.     */

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,

        String numero,
        String complemento) {

}
