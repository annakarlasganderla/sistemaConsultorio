package br.com.consultorio.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(schema = "public", name = "secretarias")
public class Secretaria extends Pessoa {

    @Getter
    @Setter
    @Digits(integer = 5, fraction = 3)
    @Column(name = "salario", nullable = false)
    private BigDecimal salario;

    @Getter
    @Setter
    @Column(name = "dataContratacao", nullable = false)
    private LocalDateTime dataContratacao;

    @Getter
    @Setter
    @Column(name = "pis", nullable = false, length = 50, unique = true)
    private String pis;

}
