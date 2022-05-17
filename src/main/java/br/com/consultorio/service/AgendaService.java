package br.com.consultorio.service;

import br.com.consultorio.Entity.Agenda;
import br.com.consultorio.Entity.Paciente;
import br.com.consultorio.Entity.Secretaria;
import br.com.consultorio.Entity.StatusAgenda;
import br.com.consultorio.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Page<Paciente> listAll(Pageable pageable) {
        return this.agendaRepository.findAll(pageable);
    }

    public Optional<Agenda> findById(Long id) {
        return this.agendaRepository.findById(id);
    }

    public void insert(Agenda agenda, String observacao,Secretaria secretaria) {
        saveTransactional(agenda);
        this.validarFormulario(agenda, secretaria);
    }

    @Transactional
    public void saveTransactional(Agenda agenda) {
        this.agendaRepository.save(agenda);
    }

    public void update(Long id, Agenda agenda, Secretaria secretaria) {
        if(id == agenda.getId()) {
            this.validarFormulario(agenda, secretaria);
            this.saveTransactional(agenda);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void updateStatus(Long id, Agenda agenda) {
        if(id == agenda.getId()) {
            this.agendaRepository.updateStatus(LocalDateTime.now(), agenda.getId());
        } else {
            throw new RuntimeException();
        }
    }

    public void validarFormulario(Agenda agenda, Secretaria secretaria) {

        if (agenda.getDataDe().compareTo(agenda.getDataAte()) >= 0) {
            throw new RuntimeException("Warning: As datas são inválidas");
        }
        if(agenda.getStatusAgenda() == null){
            agenda.setStatusAgenda(StatusAgenda.pendente);
        }
        if (secretaria == null) {
            agenda.setStatusAgenda(StatusAgenda.pendente);
        }
    }



}
