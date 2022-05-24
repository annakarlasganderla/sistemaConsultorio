package br.com.consultorio.repository;

import br.com.consultorio.Entity.Convenio;
import br.com.consultorio.Entity.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;

@Repository

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    @Modifying
    @Query("UPDATE Medico medico " +
            "SET medico.excluido = :dataExcluido " +
            "WHERE medico.id = :idMedico")

    public void updateStatus(@Param("dataExcluido") LocalDateTime dataExcluido,
                             @Param("idMedico") Long id);

    Page<Medico> findAll(Pageable pageable);
}
