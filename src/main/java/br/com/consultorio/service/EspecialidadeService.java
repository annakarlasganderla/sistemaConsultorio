package br.com.consultorio.service;

import br.com.consultorio.Entity.Especialidade;
import br.com.consultorio.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EspecialidadeService {

    @Autowired // serve como um constructor
    private EspecialidadeRepository especialidadeRepository;

    @Transactional
    public void insert(Especialidade especialidade) {
        this.especialidadeRepository.save(especialidade);
    }

    public Page<Especialidade> listAll(Pageable pageable) {
        return this.especialidadeRepository.findAll(pageable);
    }

    public Optional<Especialidade> findById(Long id) {
        return this.especialidadeRepository.findById(id);
    }

    @Transactional // avisa pro banco que os dados vao ser manipulados
    public void update(Long id, Especialidade especialidade) {
        if(id == especialidade.getId()) {
            this.especialidadeRepository.save(especialidade);
        } else {
            throw new RuntimeException();
        }
    }

    @Transactional // avisa pro banco que os dados vao ser alterados
    public void updateStatus(Long id, Especialidade especialidade) {
        if(id == especialidade.getId()) {
            this.especialidadeRepository.updateStatus(LocalDateTime.now(), especialidade.getId());
        } else {
            throw new RuntimeException();
        }

    }


}
