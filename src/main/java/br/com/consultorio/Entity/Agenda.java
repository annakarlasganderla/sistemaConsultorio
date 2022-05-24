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
    @Column(name = "status", nullable = false)
    private StatusAgenda status;

    @Getter
    @Setter
    @Column(name = "dataDe", nullable = false)
    private LocalDateTime dataDe;

    @Getter
    @Setter
    @Column(name = "dataAte", nullable = false)
    private LocalDateTime dataAte;

    @Getter
    @Setter
    @Column(name = "encaixe", nullable = false)
    private Boolean encaixe;

    @Getter
    @Setter
    @Column(name = "observacao", nullable = false)
    private String observacao;

}
