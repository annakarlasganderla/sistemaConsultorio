package br.com.consultorio.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class Pessoa extends AbstractEntily {

    @Getter
    @Setter
    @Column(name = "nome", nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Getter
    @Setter
    @Column(name = "rg", nullable = false, length = 11)
    private String rg;

    @Getter
    @Setter
    @Column(name = "cep", nullable = false, length = 8)
    private String cep;

    @Getter
    @Setter
    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;

    @Getter
    @Setter
    @Column(name = "email", nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(name = "login", nullable = false)
    private String login;

    @Getter
    @Setter
    @Column(name = "senha", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Getter
    @Setter
    @Column(name = "nacionalidade", nullable = false)
    private String nacionalidade;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo", nullable = false)
    private Sexo sexo;

}
