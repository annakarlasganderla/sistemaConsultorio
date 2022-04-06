package br.com.consultorio.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(schema = "public", name = "especialidades")
public class Especialidade extends AbstractEntily {

    @Getter
    @Setter
    @Column(name = "nome", nullable = false, length = 50, unique = true)
    private String nome;




}
