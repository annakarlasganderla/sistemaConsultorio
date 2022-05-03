package br.com.consultorio.repository;

import br.com.consultorio.Entity.Secretaria;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;

@Repository

public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {

    @Modifying
    @Query("UPDATE Secretaria secretaria" +
            "set secretaria.excluido = :dataExcluido " +
            "where secretaria.id = :secretaria")

    public void updateStatus (
            @Param("dataExcluido")LocalDateTime dataExcluido,
            @Param("secretaria") Long idSecretaria
            );

    Page<Secretaria> findAll(Pageable pageable);
}
