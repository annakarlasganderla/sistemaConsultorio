package br.com.consultorio.Entity;

public enum StatusAgenda {

    pendente("Pendente"),
    aprovado("Aprovado"),
    cancelado("Cancelado"),
    compareceu("Compareceu"),
    naoCompareceu("Não compareceu"),
    rejeitado("Rejeitado");

    public final String valor;

    private StatusAgenda(String valor){
        this.valor = valor;
    }


}
