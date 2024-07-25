package com.invext.model;

public class Solicitacao {
    private String id;
    private String tipo;

    public Solicitacao(String id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
}
