package br.com.consultorio.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(schema = "public", name = "medicos")
public class Medico extends Pessoa {

    @Getter
    @Setter
    @Column(name = "crm", nullable = false, length = 50, unique = true)
    private String CRM;

    @Getter
    @Setter
    @Digits(integer = 5, fraction = 3)
    @Column(name = "porcenParticipacao", nullable = false)
    private BigDecimal porcenParticipacao;

    @Getter
    @Setter
    @Column(name = "consultorio", nullable = false, length = 50, unique = true)
    private String consultorio;

    @Getter
    @Setter
    @JoinColumn(name = "id_especialidade", nullable = false)
    @ManyToOne
    private Especialidade especialidade;

}
