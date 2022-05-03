package br.com.consultorio.service;

import br.com.consultorio.Entity.Convenio;
import br.com.consultorio.repository.ConvenioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConvenioService {

    @Autowired
    private ConvenioRepository convenioRepository;

    @Transactional
    public void insert(Convenio convenio) {
        this.convenioRepository.save(convenio);
    }

    public Page<Convenio> listAll (Pageable pageable) {
        return this.convenioRepository.findAll(pageable);
    }

    public Optional<Convenio> findById(Long id) {
        return this.convenioRepository.findById(id);
    }

    public void update(Long id, Convenio convenio) {
        if (id == convenio.getId()){
            this.convenioRepository.save(convenio);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void updateStatus(Long id, Convenio convenio) {
       if (id == convenio.getId()) {
           this.convenioRepository.updateStatus(LocalDateTime.now(), convenio.getId());
       } else {
           throw new RuntimeException();
       }
    }
}
