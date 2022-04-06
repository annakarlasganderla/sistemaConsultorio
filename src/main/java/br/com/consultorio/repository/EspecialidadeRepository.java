package br.com.consultorio.repository;

import br.com.consultorio.Entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
}
