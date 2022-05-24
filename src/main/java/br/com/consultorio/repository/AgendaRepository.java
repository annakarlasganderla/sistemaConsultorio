package br.com.consultorio.repository;

import br.com.consultorio.Entity.Agenda;
import br.com.consultorio.Entity.StatusAgenda;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Query("SELECT Agenda FROM Agenda " +
            "WHERE :datade BETWEEN Agenda.dataDe AND Agenda.dataAte " +
            "AND :dataAte BETWEEN Agenda.dataDe AND Agenda.dataAte " +
            "AND Agenda.medico = :medico " +
            "AND Agenda.paciente = :paciente")
    public List<Agenda> conflitoMedicoPaciente(
            @Param("datade") LocalDateTime dataDe,
            @Param("dataate") LocalDateTime dataAte,
            @Param("medico") Long idMedico,
            @Param("paciente") Long idPaciente
    );

    @Query("UPDATE Agenda agenda " +
            "SET agenda.status = :agendaStatus " +
            "WHERE agenda.id = : idAgenda")
    public void updateStatus(@Param("agendaStatus") StatusAgenda agendaStatus,
                             @Param("idAgenda") Long idAgenda);

    Page<Agenda> findAll(Pageable pageable);
}
