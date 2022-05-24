package br.com.consultorio.controller;

import br.com.consultorio.Entity.Especialidade;
import br.com.consultorio.Entity.Medico;
import br.com.consultorio.service.EspecialidadeService;
import br.com.consultorio.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class MedicoController {
    @Autowired
    private MedicoService medicoService;

    @GetMapping("/{idMedico}")
    public ResponseEntity<Medico> findById(@PathVariable("idMedico") Long idMedico) {
        return ResponseEntity.ok().body(this.medicoService.findById(idMedico).get());
    }

    @GetMapping
    public ResponseEntity<Page<Medico>> listAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.medicoService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Medico medico) {
        try{
            this.medicoService.insert(medico);
            return ResponseEntity.ok().body("Especialidade cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{idMedico}")
    public ResponseEntity<?> update(@RequestBody Medico medico, @PathVariable Long idMedico) {
        try{
            this.medicoService.update(medico,idMedico );
            return ResponseEntity.ok().body("Especialidade atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/status/{idEspecialidade}")
    public ResponseEntity<?> updateStatus(@RequestBody Medico medico, @PathVariable Long idMedico) {
        try{
            this.medicoService.updateStatus(medico, idMedico);
            return ResponseEntity.ok().body("Especialidade desabilitada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
