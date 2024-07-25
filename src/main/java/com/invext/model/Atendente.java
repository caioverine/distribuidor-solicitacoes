package com.invext.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Atendente {
    private String id;
    private AtomicInteger atendimentosAtuais;

    public Atendente(String id) {
        this.id = id;
        this.atendimentosAtuais = new AtomicInteger(0);
    }

    public String getId() {
        return id;
    }

    public int getAtendimentosAtuais() {
        return atendimentosAtuais.get();
    }

    public boolean podeAtender() {
        return atendimentosAtuais.get() < 3;
    }

    public void iniciarAtendimento() {
        atendimentosAtuais.incrementAndGet();
    }

    public void finalizarAtendimento() {
        atendimentosAtuais.decrementAndGet();
    }
}
