package com.invext.model;

public enum TipoAtendimentoEnum {

    CARTOES("cartoes"),
    EMPRESTIMOS("emprestimos"),
    OUTROS("outros");

    private final String valor;

    TipoAtendimentoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }

    public static TipoAtendimentoEnum fromValor(String valor) {
        for (TipoAtendimentoEnum tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo não encontrado: " + valor);
    }
}
