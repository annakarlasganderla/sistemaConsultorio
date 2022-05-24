package br.com.consultorio.controller;

import br.com.consultorio.Entity.Convenio;
import br.com.consultorio.Entity.Especialidade;
import br.com.consultorio.service.ConvenioService;
import br.com.consultorio.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

public class ConvenioController {

    @Autowired
    private ConvenioService convenioService;

    @GetMapping("/{idConvenio}")
    public ResponseEntity<Convenio> findById(@PathVariable("idConvenio") Long idConvenio) {
        return ResponseEntity.ok().body(this.convenioService.findById(idConvenio).get());
    }

    @GetMapping
    public ResponseEntity<Page<Convenio>> listAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.convenioService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Convenio convenio) {
        try{
            this.convenioService.insert(convenio);
            return ResponseEntity.ok().body("Convenio cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{idConvenio}")
    public ResponseEntity<?> update(@RequestBody Convenio convenio, @PathVariable Long idConvenio) {
        try{
            this.convenioService.update(idConvenio, convenio);
            return ResponseEntity.ok().body("Especialidade atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/status/{idConvenio}")
    public ResponseEntity<?> updateStatus(@RequestBody Convenio convenio, @PathVariable Long idConvenio) {
        try{
            this.convenioService.updateStatus(idConvenio, convenio);
            return ResponseEntity.ok().body("Especialidade desabilitada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
