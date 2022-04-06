package br.com.consultorio.repository;

import br.com.consultorio.Entity.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
}
