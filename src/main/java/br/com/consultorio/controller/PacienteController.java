package br.com.consultorio.controller;

import br.com.consultorio.Entity.Especialidade;
import br.com.consultorio.Entity.Paciente;
import br.com.consultorio.service.EspecialidadeService;
import br.com.consultorio.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/{idPaciente}")
    public ResponseEntity<Paciente> findById(@PathVariable("idPaciente") Long idPaciente) {
        return ResponseEntity.ok().body(this.pacienteService.findById(idPaciente).get());
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> listAll(Pageable pageable) {
        return ResponseEntity.ok().body(this.pacienteService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Paciente paciente) {
        try{
            this.pacienteService.insert(paciente);
            return ResponseEntity.ok().body("Paciente cadastrada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<?> update(@RequestBody Paciente paciente, @PathVariable Long idPaciente) {
        try{
            this.pacienteService.update(idPaciente, paciente);
            return ResponseEntity.ok().body("Paciente atualizada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/status/{idPaciente}")
    public ResponseEntity<?> updateStatus(@RequestBody Paciente paciente, @PathVariable Long idPaciente) {
        try{
            this.pacienteService.updateStatus(idPaciente, paciente);
            return ResponseEntity.ok().body("Paciente desabilitado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
