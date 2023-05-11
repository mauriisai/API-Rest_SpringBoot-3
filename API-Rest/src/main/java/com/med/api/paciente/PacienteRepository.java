package com.med.api.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Con esta interfaz seremos capaces de hacer toda la gestion del CRUD con la BD,
// los param. que tenemos que mandar son: <Tipo de Obj a Persistir, tipo de Dato del ID>
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findByActivoTrue(Pageable paginacion);
}
