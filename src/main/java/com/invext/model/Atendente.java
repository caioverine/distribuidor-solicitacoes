package com.invext.model;

import java.util.LinkedList;
import java.util.Queue;

public class Atendente {
    private String id;
    private Queue<Solicitacao> solicitacoesEmAtendimento;

    public Atendente(String id) {
        this.id = id;
        this.solicitacoesEmAtendimento = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public Queue<Solicitacao> getSolicitacoesEmAtendimento() {
        return solicitacoesEmAtendimento;
    }

    public boolean podeAtender() {
        return solicitacoesEmAtendimento.size() < 3;
    }

    public void adicionarSolicitacao(Solicitacao solicitacao) {
        if(solicitacoesEmAtendimento.size() < 3) {
            solicitacoesEmAtendimento.offer(solicitacao);
        } else {
            throw new RuntimeException("Atendente já está atendendo o máximo de solicitações.");
        }
    }

    public void removerSolicitacao(Solicitacao solicitacao) {
        solicitacoesEmAtendimento.remove(solicitacao);
    }

    @Override
    public String toString() {
        return "Atendente{id='" + id + "'}";
    }
}
