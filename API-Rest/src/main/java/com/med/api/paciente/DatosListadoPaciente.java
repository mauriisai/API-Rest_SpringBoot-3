package com.med.api.paciente;

public record DatosListadoPaciente(Long id, String identidad,String nombre,String telefono,String calle, String ciudad) {

    // Creamos un constructor para mapearla en el listado paciente a mostrar.
    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getIdentidad(),paciente.getNombre(),paciente.getTelefono(),
                paciente.getDireccion().getCalle(),paciente.getDireccion().getCiudad());
    }
}
