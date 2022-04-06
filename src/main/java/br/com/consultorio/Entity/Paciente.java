package br.com.consultorio.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(schema = "public", name = "pacientes")
public class Paciente extends Pessoa{

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoAtendimento", nullable = false)
    private TipoAtendimento tipoAtendimento;

    @Getter
    @Setter
    @Column(name = "numeroCartaoConvenio", nullable = false, length = 50, unique = true)
    private String numeroCartaoConvenio;

    @Getter
    @Setter
    @Column(name = "dataVencimento", nullable = false)
    private LocalDateTime dataVencimento;

    @Getter
    @Setter
    @JoinColumn(name = "id_convenio", nullable = false)
    @ManyToOne
    private Convenio convenio;

}
