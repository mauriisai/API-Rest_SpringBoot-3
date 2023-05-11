package com.med.api.paciente;

import com.med.api.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarPaciente(@NotNull Long id, String nombre, String telefono, DatosDireccion direccion) {
}
