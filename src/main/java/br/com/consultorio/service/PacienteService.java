package br.com.consultorio.service;

import br.com.consultorio.Entity.Especialidade;
import br.com.consultorio.Entity.Paciente;
import br.com.consultorio.Entity.TipoAtendimento;
import br.com.consultorio.repository.PacienteRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void insert(Paciente paciente) {
        validarFormulario(paciente);
        saveTransactional(paciente);
    }

    public Page<Paciente> listAll(Pageable pageable) {
        return this.pacienteRepository.findAll(pageable);
    }

    public Optional<Paciente> findById(Long id) {
        return this.pacienteRepository.findById(id);
    }
    
    public void update(Long id, Paciente paciente) {
        if(id == paciente.getId()) {
            this.validarFormulario(paciente);
            this.saveTransactional(paciente);
        } else {
            throw new RuntimeException();
        }
    }

   @Transactional
   public void updateStatus(Long id, Paciente paciente) {
        if(id == paciente.getId()) {
            this.pacienteRepository.updateStatus(LocalDateTime.now(), paciente.getId());
        } else {
            throw new RuntimeException();
        }
   }

    @Transactional
    public void saveTransactional (Paciente paciente) {
        this.pacienteRepository.save(paciente);
    }

    public void validarFormulario(Paciente paciente) {
        if(paciente.getTipoAtendimento().equals(TipoAtendimento.convenio) ) {
            if(paciente.getConvenio() == null || paciente.getConvenio().getId() == null) {
                throw  new RuntimeException(" Convenio não informado ");
            }
            if(paciente.getNumeroCartaoConvenio() == null) {
                throw  new RuntimeException(" Cartão não informado ");
            }
            if(paciente.getDataVencimento() == null) {
                throw  new RuntimeException(" Data de vencimento do cartão não informado ");
            }
            if(paciente.getDataVencimento().compareTo(LocalDateTime.now()) > 0) {
                throw  new RuntimeException(" Data de vencimento está expirado ");
            }
        }

        if(paciente.getTipoAtendimento().equals(TipoAtendimento.particular)) {
            paciente.setConvenio(null);
            paciente.setNumeroCartaoConvenio(null);
            paciente.setDataVencimento(null);
        }
    }

}
