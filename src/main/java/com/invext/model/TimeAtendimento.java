package com.invext.model;

import java.util.LinkedList;
import java.util.Queue;

public class TimeAtendimento {
    private TipoAtendimentoEnum tipo;
    private Queue<Atendente> atendentes;

    public TimeAtendimento(TipoAtendimentoEnum tipoTimeAtendimento) {
        this.tipo = tipoTimeAtendimento;
        this.atendentes = new LinkedList<>();
    }

    public TipoAtendimentoEnum getTipo() {
        return tipo;
    }

    public Queue<Atendente> getAtendentes() {
        return atendentes;
    }

    public void adicionarAtendente(Atendente atendente) {
        atendentes.offer(atendente);
    }

    public Atendente obterAtendenteDisponivel() {
        for (Atendente atendente : atendentes) {
            if (atendente.podeAtender()) {
                return atendente;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "TimeAtendimento{tipo='" + tipo + "', atendentes=" + atendentes + "}";
    }
}
