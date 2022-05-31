package br.com.consultorio.service;

import br.com.consultorio.Entity.Secretaria;
import br.com.consultorio.repository.SecretariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SecretariaService {

    @Autowired
    public SecretariaRepository secretariaRepository;

    @Transactional
    public void insert(Secretaria secretaria) {
        this.secretariaRepository.save(secretaria);
    }

    public Page<Secretaria> listAll (Pageable pageable) {
        return this.secretariaRepository.findAll(pageable);
    }

    public Optional<Secretaria> findById(Long id) {
        return this.secretariaRepository.findById(id);
    }

    public void update(Long id, Secretaria secretaria) {
        if (id == secretaria.getId()) {
            this.secretariaRepository.save(secretaria);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void updateStatus(Long id, Secretaria secretaria) {
        if (id == secretaria.getId()) {
            this.secretariaRepository.updateStatus(LocalDateTime.now(), secretaria.getId());
        } else {
            throw new RuntimeException();
        }
    }


}
