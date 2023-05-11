package com.med.api.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Metodo creado para Filtrar los Registros con Campo 'activo = true' en la Tabla de BD
    Page<Medico> findByActivoTrue(Pageable paginacion);
}
