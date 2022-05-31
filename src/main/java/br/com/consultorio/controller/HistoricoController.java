package br.com.consultorio.controller;

import br.com.consultorio.Entity.Especialidade;
import br.com.consultorio.Entity.Historico;
import br.com.consultorio.service.EspecialidadeService;
import br.com.consultorio.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/historicos")
public class HistoricoController {
    @Autowired
    private HistoricoService historicoService;

    @GetMapping("/{idHistorico}")
    public ResponseEntity<Historico> findById(@PathVariable("idHistorico") Long idHistorico) {
        return ResponseEntity.ok().body(this.historicoService.findById(idHistorico).get());
    }

    @GetMapping
    public ResponseEntity<Page<Historico>> listAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.historicoService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Historico historico) {
        try{
            this.historicoService.insert(historico);
            return ResponseEntity.ok().body("Historico cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
