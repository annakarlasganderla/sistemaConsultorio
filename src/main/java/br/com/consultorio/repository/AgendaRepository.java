package br.com.consultorio.repository;

import br.com.consultorio.Entity.Agenda;
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
public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    @Modifying
    @Query("update Agenda agendaa" +
            "set agenda.excluido = :dataExcluido " +
            "where agenda.id = :agenda")
    public void updateStatus (
            @Param("dataExcluido") LocalDateTime dataExcluido,
            @Param("agenda") Long idAgenda);

    Page<Paciente> findAll(Pageable pageable);
}
