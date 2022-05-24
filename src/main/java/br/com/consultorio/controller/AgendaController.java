package br.com.consultorio.controller;

import br.com.consultorio.Entity.Agenda;
import br.com.consultorio.Entity.Especialidade;
import br.com.consultorio.service.AgendaService;
import br.com.consultorio.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

public class AgendaController {
    @Autowired
    private AgendaService agendaService;

    @GetMapping("/{idAgenda}")
    public ResponseEntity<Agenda> findById(@PathVariable("idAgenda") Long idAgenda) {
        return ResponseEntity.ok().body(this.agendaService.findById(idAgenda).get());
    }

    @GetMapping
    public ResponseEntity<Page<Agenda>> listAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.agendaService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Agenda agenda) {
        try{
            this.agendaService.insert(agenda);
            return ResponseEntity.ok().body("Agenda cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{idAgenda}")
    public ResponseEntity<?> update(@RequestBody Agenda agenda , @PathVariable Long idAgenda) {
        try{
            this.agendaService.update(idAgenda, agenda);
            return ResponseEntity.ok().body("Agenda atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/status/{idAgenda}")
    public ResponseEntity<?> updateStatus(@RequestBody Agenda agenda, @PathVariable Long idAgenda) {
        try{
            this.agendaService.updateStatus(idAgenda, agenda);
            return ResponseEntity.ok().body("Especialidade desabilitada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
