package br.com.consultorio.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(schema = "public", name = "convenios")
public class Convenio extends AbstractEntily{

    @Getter
    @Setter
    @Column(name = "nome", nullable = false, length = 50, unique = true)
    private String nome;

    @Getter
    @Setter
    @Digits(integer = 5, fraction = 3)
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
}

