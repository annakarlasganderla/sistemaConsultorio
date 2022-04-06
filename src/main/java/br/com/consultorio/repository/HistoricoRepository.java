package br.com.consultorio.repository;

import br.com.consultorio.Entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}
