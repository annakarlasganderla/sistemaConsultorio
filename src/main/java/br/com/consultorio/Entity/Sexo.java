package br.com.consultorio.Entity;

public enum Sexo {


    masculino("Masculino"),
    feminino("Feminino"),
    outros("Outros");

    public final String valor;

    private Sexo(String valor) {
        this.valor = valor;
    }
}
