package com.med.api.paciente;

import com.med.api.direccion.Direccion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private Boolean activo;
    private String telefono;
    private String identidad;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.activo = true;
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.identidad = datosRegistroPaciente.identidad();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public void actualizarPaciente(DatosActualizarPaciente datosActualizarPaciente) {
        if(datosActualizarPaciente.nombre() != null) {
            this.nombre = datosActualizarPaciente.nombre();
        }
        if(datosActualizarPaciente.telefono()!= null) {
            this.telefono = datosActualizarPaciente.telefono();
        }
        if(datosActualizarPaciente.direccion()!= null) {
            this.direccion = direccion.actualizarDatos(datosActualizarPaciente.direccion());
        }
    }

    public void inhabilitarPaciente() {
        this.activo = false;
    }
}
