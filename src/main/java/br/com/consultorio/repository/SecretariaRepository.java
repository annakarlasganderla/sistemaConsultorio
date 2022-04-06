package br.com.consultorio.repository;

import br.com.consultorio.Entity.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {
}
