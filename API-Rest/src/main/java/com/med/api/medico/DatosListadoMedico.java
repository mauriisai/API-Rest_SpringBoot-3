package com.med.api.medico;

// DTO creado para mostrar unicamente los campos seleccinados por regla de negocios en la app.
public record DatosListadoMedico(Long id, String nombre, String especialidad, String documento, String email) {

    public DatosListadoMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(), medico.getDocumento(), medico.getEmail());
    }
}
