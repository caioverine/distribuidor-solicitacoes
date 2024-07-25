package com.invext.model;

import java.util.*;

public class TimeAtendimento {
    private String nome;
    private List<Atendente> atendentes;
    private Queue<Solicitacao> filaEspera;

    public TimeAtendimento(String nome) {
        this.nome = nome;
        this.atendentes = new ArrayList<>();
        this.filaEspera = new LinkedList<>();
    }

    public void adicionarAtendente(Atendente atendente) {
        atendentes.add(atendente);
    }

    public void adicionarSolicitacao(Solicitacao solicitacao) {
        for (Atendente atendente : atendentes) {
            System.out.println(atendente.getAtendimentosAtuais());
            System.out.println(atendente.podeAtender());
            if (atendente.podeAtender()) {
                atendente.iniciarAtendimento();
                return;
            }
        }
        filaEspera.add(solicitacao);
    }

    public void liberarAtendente(Atendente atendente) {
        atendente.finalizarAtendimento();
        if (!filaEspera.isEmpty()) {
            Solicitacao solicitacao = filaEspera.poll();
            atendente.iniciarAtendimento();
        }
    }

    public String getStatus() {
        return String.format("Time: %s, Atendentes: %d, Fila de Espera: %d", nome, atendentes.size(), filaEspera.size());
    }
}
