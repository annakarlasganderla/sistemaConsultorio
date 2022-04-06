package br.com.consultorio.Entity;

public enum TipoAtendimento {

    convenio("Convenio"),
    particular("Particular");

    public final String valor;

    private TipoAtendimento(String valor){
        this.valor = valor;
    }

}
