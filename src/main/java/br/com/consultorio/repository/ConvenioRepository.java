package br.com.consultorio.repository;

import br.com.consultorio.Entity.Convenio;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDateTime;

@Repository

public interface ConvenioRepository extends JpaRepository<Convenio, Long> {

    @Modifying
    @Query("update Convenio convenio" +
            "set convenio.excluido = :dataExcluido " +
            "where convenio.id = :convenio")
    public void updateStatus (
            @Param("dataExcluido")LocalDateTime dataExcluido,
            @Param("convenio") Long idConvenio);


    Page<Convenio> findAll(Pageable pageable);
}
