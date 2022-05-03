package br.com.consultorio.repository;

import br.com.consultorio.Entity.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
