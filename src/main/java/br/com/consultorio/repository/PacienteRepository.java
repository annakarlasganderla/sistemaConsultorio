package br.com.consultorio.repository;

import br.com.consultorio.Entity.Medico;
import br.com.consultorio.Entity.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Modifying
    @Query("update Paciente paciente" +
            "set paciente.excluido = :dataExcluido " +
            "where paciente.id = :paciente")
    public void updateStatus (
            @Param("dataExluido")LocalDateTime dataExcluido,
            @Param("paciente") Long idPaciente
            );

    Page<Paciente> findAll(Pageable pageable);

}
