package br.com.consultorio.controller;

import br.com.consultorio.Entity.Especialidade;
import br.com.consultorio.Entity.Secretaria;
import br.com.consultorio.service.EspecialidadeService;
import br.com.consultorio.service.SecretariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@Controller
@RequestMapping("api/secretarias")
public class SecretariaController {
    @Autowired
    private SecretariaService secretariaService;

    @GetMapping("/{idSecretaria}")
    public ResponseEntity<Secretaria> findById(@PathVariable("idSecretaria") Long idSecretaria) {
        return ResponseEntity.ok().body(this.secretariaService.findById(idSecretaria).get());
    }

    @GetMapping
    public ResponseEntity<Page<Secretaria>> listAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.secretariaService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Secretaria secretaria) {
        try{
            this.secretariaService.insert(secretaria);
            return ResponseEntity.ok().body("secretaria cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{idSecretaria}")
    public ResponseEntity<?> update(@RequestBody Secretaria secretaria, @PathVariable Long idSecretaria) {
        try{
            this.secretariaService.update(idSecretaria, secretaria);
            return ResponseEntity.ok().body("secretaria atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/status/{idSecretaria}")
    public ResponseEntity<?> updateStatus(@RequestBody Secretaria secretaria, @PathVariable Long idSecretaria) {
        try{
            this.secretariaService.updateStatus(idSecretaria, secretaria);
            return ResponseEntity.ok().body("secretaria desabilitada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
