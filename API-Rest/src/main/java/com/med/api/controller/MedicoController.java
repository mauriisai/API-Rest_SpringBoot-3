package com.med.api.controller;

import com.med.api.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indicamos mediante las anotaciones que nuestra clase es un 'RestController' y
// que mapearemos el Request en la Url "/medicos" de nuestro app
@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    //Indicamos que nuestro metodo "registarMedicos" realizara mapeamiento
    // del tipo Post porq recibiremos un Request que sera recibido en la variable tipo String 'param'
    @PostMapping
    public void registrarMedicos(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){
        // mediante la anotacion RequestBody indicamos que la variable 'param' es el body del Request
        // para prueba podemos imprimir el String JSon recibido -> System.out.println(datosRegistroMedico);

        medicoRepository.save(new Medico(datosRegistroMedico));
    }

    // ---- OPCION 2 ---- Usando Paginacion -> imprimiendo un Page<Tipo de Dato DTO>
    @GetMapping
    // Usamos la Anotacion @PageableDefault para establecer valores a los QueryPAtterns (itemsXPage, orderBy, ...)
    public Page<DatosListadoMedico> listadoMedicos(@PageableDefault(sort = "nombre", size = 4) Pageable paginacion) {
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
        // MOSTRAR SOLO MEDICOS ACTIVOS
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new);
    }

    /* ---- OPCION 1 ---- imprimiendo un List<Tipo de Dato DTO>
    // Metodo para formatear los datos a mostrar respondiendo a la Request

    public List<DatosListadoMedico> listadoMedicos(){

    // Creando respuesta a la solicitud del metodo GET de la app, generando la lista de medicos
        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList();   }       */


    // Metodo para actualizar param de un Registro de Medico
    @PutMapping
    @Transactional
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico =medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
    }
    /* EXCLUIR UN REGISTRO DE LA BD mediante Update del Campo 'activo' enviando el ID como PathVariable */
    // En las variables de la Anotacion @DeleteMapping tenemos que generar un 'PathVariable'
    @DeleteMapping("/ex-{id}")
    @Transactional
    public void excluirMedico(@PathVariable Long id) {
        Medico medico =medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
    }

    /*  DELETE DE UN REGISTRO DE LA BD mediante la declaracion de ID como PathVariable
        En las variables de la Anotacion @DeleteMapping tenemos que generar un 'PathVariable'   */
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id) {
        Medico medico =medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }
}

