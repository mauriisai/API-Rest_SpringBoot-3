package com.med.api.medico;

import com.med.api.direccion.DatosDireccion;
import com.med.api.direccion.Direccion;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private Boolean activo;
    private String telefono;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    // Constructor para setear en las instancias de la clase Medico, los atributos recibidos
    // en el param 'DatosRegistroMedico' mandados por el cliente por medio del JSON
    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo=true;
        this.nombre=datosRegistroMedico.nombre();
        this.email=datosRegistroMedico.email();
        this.documento=datosRegistroMedico.documento();
        this.especialidad=datosRegistroMedico.especialidad();
        this.telefono=datosRegistroMedico.telefono();
        this.direccion= new Direccion(datosRegistroMedico.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.nombre() != null) {
            this.nombre = datosActualizarMedico.nombre();
        }
        if(datosActualizarMedico.documento() != null) {
            this.documento = datosActualizarMedico.documento();
        }
        if(datosActualizarMedico.direccion() != null) {
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }
    }

    // Excluyendo Medico de la generacion de lista Activa
    public void desactivarMedico() {
        this.activo = false;
    }
}
