package com.med.api.controller;

import com.med.api.medico.*;
import com.med.api.paciente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

// Indicamos mediante las anotaciones que nuestra clase es un 'RestController' y
// que mapearemos el Request en la Url "/pacientes" de nuestro app
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    @PostMapping
    public void registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente){
        // mediante la anotacion @RequestBody indicamos que la variable 'datosRegistroPaciente' es el body del Request
        // para prueba podemos imprimir el String JSon recibido -> System.out.println(datosRegistroPaciente);
        // Transformando los datos para persistirlos en la BD
        pacienteRepository.save(new Paciente(datosRegistroPaciente));

    }

    @GetMapping("/registrosActivos")
    public Page<DatosListadoPaciente> pacientesActivos(@PageableDefault(size = 5) Pageable paginacion){
        return pacienteRepository.findByActivoTrue(paginacion).map(DatosListadoPaciente::new);
    }

    //  Usando Paginacion -> imprimiendo un Page<Tipo de Dato DTO>
    @GetMapping
    public Page<DatosListadoPaciente> listarPacientes(@PageableDefault(size = 5, sort= "identidad") Pageable paginacion){
        return pacienteRepository.findAll(paginacion).map(DatosListadoPaciente::new);
    }

    @PutMapping
    @Transactional
    public void actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente){
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarPaciente(datosActualizarPaciente);
    }

    // METODO DE EXCLUSION DE PACIENTES INACTIVOS seteando una variable de la BD sin borrar el registro
    // Mostrando persistencia en el tiempo de vida del negocio.
    @DeleteMapping("/ex-{id}")
    @Transactional
    public void excluirPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inhabilitarPaciente();
    }
}

