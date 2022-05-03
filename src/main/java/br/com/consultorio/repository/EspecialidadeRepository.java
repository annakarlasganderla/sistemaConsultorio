package br.com.consultorio.repository;

import br.com.consultorio.Entity.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    @Modifying
    @Query("UPDATE Especialidade especialidade " +
            "SET especialidade.excluido = :dataExcluido " +
            "WHERE especialidade.id = :especialidade")
    public void updateStatus(
            @Param("dataExcluido") LocalDateTime dataExcluido,
            @Param("especialidade") Long idEspecialidade);

    Page<Especialidade> findAll(Pageable pageable);
}
