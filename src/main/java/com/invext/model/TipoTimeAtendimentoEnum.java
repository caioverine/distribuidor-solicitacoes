package com.invext.model;

public enum TipoTimeAtendimentoEnum {

    CARTOES("cartoes"),
    EMPRESTIMOS("emprestimos"),
    OUTROS("outros");

    private final String valor;

    TipoTimeAtendimentoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }

    public static TipoTimeAtendimentoEnum fromValor(String valor) {
        for (TipoTimeAtendimentoEnum tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo não encontrado: " + valor);
    }
}
