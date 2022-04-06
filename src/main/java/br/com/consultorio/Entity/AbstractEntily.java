package br.com.consultorio.Entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class AbstractEntily {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Column(name = "cadastro", nullable = false)
    private LocalDateTime cadastro;

    @Getter
    @Setter
    @Column(name = "atualizado")
    private LocalDateTime atualizado;

    @Getter
    @Setter
    @Column(name = "excluido")
    private LocalDateTime excluido;


    @PrePersist
    public void atualizarDataCadastro() {
        this.cadastro = LocalDateTime.now();
    }

    @PreUpdate
    public void atualizarDataAtualizar() {
        this.atualizado = LocalDateTime.now();
    }


}
