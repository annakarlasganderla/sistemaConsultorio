package br.com.consultorio.Entity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(schema = "public", name = "historicos")
public class Historico extends AbstractEntily {

    @Getter
    @Setter
    @JoinColumn(name = "id_agenda", nullable = false)
    @ManyToOne
    private Agenda agenda;

    @Getter
    @Setter
    @JoinColumn(name = "id_paciente", nullable = false)
    @ManyToOne
    private Paciente paciente;

    @Getter
    @Setter
    @JoinColumn(name = "id_secretaria", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Secretaria secretaria;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "statusAgenda", nullable = false)
    private StatusAgenda statusAgenda;

    @Getter
    @Setter
    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Getter
    @Setter
    @Column(name = "observacao", nullable = true, length = 100, unique = false)
    private String observacao;


}
