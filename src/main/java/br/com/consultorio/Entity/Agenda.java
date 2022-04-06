package br.com.consultorio.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(schema = "public", name = "agendas")
public class Agenda extends AbstractEntily{

    @Getter
    @Setter
    @JoinColumn(name = "id_paciente", nullable = false)
    @ManyToOne
    private Paciente paciente;

    @Getter
    @Setter
    @JoinColumn(name = "id_medico", nullable = false)
    @ManyToOne
    private Medico medico;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "statusAgenda", nullable = false)
    private StatusAgenda statusAgenda;

    @Getter
    @Setter
    @Column(name = "dataAgendamento", nullable = false)
    private LocalDateTime dataAgendamento;

    @Getter
    @Setter
    @Column(name = "encaixe", nullable = false)
    private Boolean encaixe;

}
