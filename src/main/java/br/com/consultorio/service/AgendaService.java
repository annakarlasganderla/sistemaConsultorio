package br.com.consultorio.service;

import br.com.consultorio.Entity.*;
import br.com.consultorio.repository.AgendaRepository;
import br.com.consultorio.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.awt.print.Pageable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    private Historico historico;

    public Page<Agenda> listAll(Pageable pageable) {
        return this.agendaRepository.findAll(pageable);
    }

    public Optional<Agenda> findById(Long id) {
        return this.agendaRepository.findById(id);
    }

    public void insert(Agenda agenda) {
        saveTransaction(agenda);
        this.validarFormulario(agenda);
    }

    public void updateStatusToRejeitado(Agenda agenda, Secretaria secretaria)
    {
        this.updateStatusPendenteToRejeitado(agenda, secretaria);
        this.saveTransaction(agenda);
    }

    public void updateStatusToAprovado(Agenda agenda, Secretaria secretaria)
    {
        this.updateStatusPendenteToAprovado(agenda, secretaria);
        this.saveTransaction(agenda);
    }

    public void updateStatusToCancelado(Agenda agenda, Secretaria secretaria, Paciente paciente)
    {
        this.updateStatusPendenteOrAprovadoToCancelado(agenda, secretaria, paciente);
        this.saveTransaction(agenda);
    }

    public void updateStatusToCompareceu(Agenda agenda, Secretaria secretaria)
    {
        this.updateStatusAprovadoToCompareceu(agenda, secretaria);
    }

    public void updateStatusToNaoCompareceu(Agenda agenda, Secretaria secretaria)
    {
        this.updateStatusAprovadoToNaoCompareceu(agenda, secretaria);
        this.saveTransaction(agenda);
    }

    public void updateStatusPendenteToRejeitado(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            Assert.isTrue(agendaRepository.findById(agenda.getId()).get().getStatus().equals(StatusAgenda.pendente), "Assert = false");
            Assert.isTrue(agenda.getStatus().equals(StatusAgenda.rejeitado), "Assert = false");

            agendaRepository.updateStatus(agenda.getStatus(), agenda.getId());

             historico = new Historico(agenda, agenda.getPaciente() ,secretaria,agenda.getStatus(),LocalDateTime.now(),agenda.getObservacao());
            historicoRepository.save(historico);
        }
    }

    public void updateStatusPendenteToAprovado(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            Assert.isTrue(agendaRepository.findById(agenda.getId()).get().getStatus().equals(StatusAgenda.pendente), "Assert = false");
            Assert.isTrue(agenda.getStatus().equals(StatusAgenda.aprovado), "Assert = false");

            agendaRepository.updateStatus(agenda.getStatus(), agenda.getId());

            historico = new Historico(agenda, agenda.getPaciente() ,secretaria,agenda.getStatus(),LocalDateTime.now(),agenda.getObservacao());

            historicoRepository.save(historico);
        }
    }

    public void updateStatusPendenteOrAprovadoToCancelado(Agenda agenda, Secretaria secretaria, Paciente paciente)
    {
        if(secretaria != null || paciente != null)
        {
            if(agendaRepository.getById(agenda.getId()).getStatus().equals(StatusAgenda.pendente)
                    || agendaRepository.getById(agenda.getId()).getStatus().equals(StatusAgenda.aprovado))
            {
                agendaRepository.updateStatus(agenda.getStatus(), agenda.getId());

                historico = new Historico(agenda, agenda.getPaciente() ,secretaria,agenda.getStatus(),LocalDateTime.now(),agenda.getObservacao());

                historicoRepository.save(historico);
            }
        }
    }

    public void updateStatusAprovadoToCompareceu(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            Assert.isTrue(agendaRepository.getById(agenda.getId()).getStatus().equals(StatusAgenda.aprovado), "Assets = false");
            Assert.isTrue(dataPassada(agenda.getDataDe(), agenda.getDataAte()), "Assets = false");

            agendaRepository.updateStatus(agenda.getStatus(), agenda.getId());

            historico = new Historico(agenda, agenda.getPaciente() ,secretaria,agenda.getStatus(),LocalDateTime.now(),agenda.getObservacao());

            historicoRepository.save(historico);

        }
    }

    public void updateStatusAprovadoToNaoCompareceu(Agenda agenda, Secretaria secretaria)
    {
        if(secretaria != null)
        {
            Assert.isTrue(agendaRepository.getById(agenda.getId()).getStatus().equals(StatusAgenda.aprovado), "Assets = false");
            Assert.isTrue(dataPassada(agenda.getDataDe(), agenda.getDataAte()), "Assets = false");

            agendaRepository.updateStatus(agenda.getStatus(), agenda.getId());

            historico = new Historico(agenda, agenda.getPaciente() ,secretaria,agenda.getStatus(),LocalDateTime.now(),agenda.getObservacao());

            historicoRepository.save(historico);
        }
    }

    @Transactional
    public void saveTransaction(Agenda agenda) {
        this.agendaRepository.save(agenda);
    }

    public void update(Long id, Agenda agenda) {
        if(id == agenda.getId()) {
            this.validarFormulario(agenda);
            this.saveTransaction(agenda);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void updateStatus(Long id, Agenda agenda) {
        if(id == agenda.getId()) {
            this.agendaRepository.updateStatus(agenda.getStatus(), agenda.getId());
        } else {
            throw new RuntimeException();
        }
    }

    public void validarFormulario(Agenda agenda) {

        if (agenda.getDataDe().compareTo(agenda.getDataAte()) >= 0) {
            throw new RuntimeException("Warning: As datas são inválidas");
        }
        if(agenda.getStatus() == null){
            agenda.setStatus(StatusAgenda.pendente);
        }

    }

    private boolean dataValida(LocalDateTime dataDe, LocalDateTime dataAte)
    {
        if(dataDe.isAfter(LocalDateTime.now())
                &&
                dataAte.isAfter(LocalDateTime.now()))
        {
            if(dataDe.isBefore(dataAte))
            {
                return true;
            }
        }
        return false;
    }

    private boolean dataPassada(LocalDateTime dataDe, LocalDateTime dataAte)
    {
        if(dataDe.isBefore(LocalDateTime.now())
                && dataAte.isBefore(LocalDateTime.now()))
        {
            return true;
        }
        return false;
    }

    private boolean horarioValido(LocalDateTime data)
    {
        if(data.getHour() > 8 && data.getHour() < 12
                ||
                data.getHour() > 14 && data.getHour() < 18)
        {
            return true;
        }
        return false;
    }

    private boolean diaValido(LocalDateTime data)
    {
        return !data.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                &&
                !data.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                ? false : true;
    }

    private boolean horariosMedicosEPacientes(Agenda agenda)
    {
        if(agendaRepository.conflitoMedicoPaciente(
                agenda.getDataDe(),
                agenda.getDataAte(),
                agenda.getMedico().getId(),
                agenda.getPaciente().getId()
        ).size() > 0)
        {
            return true;
        }
        return false;
    }

    private void validacoesPadroes(Agenda agenda)
    {
        Assert.isTrue(!agenda.getEncaixe(), "Assets = false");
        Assert.isTrue(dataValida(agenda.getDataDe(), agenda.getDataAte()), "Assets = false");
        Assert.isTrue(horarioValido(agenda.getDataDe()), "Assets = false");
        Assert.isTrue(horarioValido(agenda.getDataAte()), "Assets = false");
        Assert.isTrue(diaValido(agenda.getDataDe()), "Assets = false");
        Assert.isTrue(diaValido(agenda.getDataAte()), "Assets = false");
        Assert.isTrue(horariosMedicosEPacientes(agenda), "Assets = false");
    }



}
