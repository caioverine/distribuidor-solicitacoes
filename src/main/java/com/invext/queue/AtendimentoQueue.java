package com.invext.queue;

import com.invext.model.Atendente;
import com.invext.model.Solicitacao;
import com.invext.model.TimeAtendimento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Component
public class AtendimentoQueue {

    private static final Logger logger = LoggerFactory.getLogger(AtendimentoQueue.class);

    private Map<String, Queue<Solicitacao>> filaAtendimento = new HashMap<>();
    private Map<String, TimeAtendimento> timesAtendimento = new HashMap<>();

    public AtendimentoQueue() {
        filaAtendimento.put("cartoes", new LinkedList<>());
        filaAtendimento.put("emprestimos", new LinkedList<>());
        filaAtendimento.put("outros", new LinkedList<>());
    }

    public void adicionarSolicitacao(String tipo, Solicitacao solicitacao) {
        logger.debug("Adicionando solicitação à fila de atendimento: {}", solicitacao.getId());
        TimeAtendimento timeAtendimento = timesAtendimento.get(tipo);
        if (timeAtendimento == null) {
            filaAtendimento.get(tipo).offer(solicitacao);
            logger.debug("Solicitação adicionada à fila de tipo: {}", tipo);
        } else {
            Atendente atendente = timeAtendimento.obterAtendenteDisponivel();
            if (atendente != null) {
                try {
                    atendente.adicionarSolicitacao(solicitacao);
                    logger.debug("Solicitação atribuída ao atendente: {}", atendente.getId());
                } catch (RuntimeException e) {
                    filaAtendimento.get(tipo).offer(solicitacao);
                    logger.debug("Nenhum espaço disponível. Solicitação adicionada à fila de tipo: {}", tipo);
                }
            } else {
                filaAtendimento.get(tipo).offer(solicitacao);
                logger.debug("Nenhum atendente disponível. Solicitação adicionada à fila de tipo: {}", tipo);
            }
        }
    }

    public Solicitacao processarSolicitacao(String tipo) {
        logger.debug("Processando solicitação do tipo: {}", tipo);
        TimeAtendimento timeAtendimento = timesAtendimento.get(tipo);
        if (timeAtendimento != null) {
            logger.debug("Processando solicitação com o time: {}", timeAtendimento);
            for (Atendente atendente : timeAtendimento.getAtendentes()) {
                if (!atendente.getSolicitacoesEmAtendimento().isEmpty()) {
                    logger.debug("Processando solicitação com o atendente: {}", atendente);
                    Solicitacao solicitacao = atendente.getSolicitacoesEmAtendimento().poll();
                    logger.debug("Solicitação processada: {}", solicitacao.getId());
                    return solicitacao;
                } else {
                    logger.debug("Não há solicitações para serem processadas pelo atendente {}", atendente.getId());
                }
            }
        }
        return null;
    }

    public void finalizarAtendimento(String tipo, Solicitacao solicitacao) {
        logger.debug("Finalizando atendimento para solicitação: {}", solicitacao);
        TimeAtendimento timeAtendimento = timesAtendimento.get(tipo);
        if (timeAtendimento != null) {
            for (Atendente atendente : timeAtendimento.getAtendentes()) {
                atendente.removerSolicitacao(solicitacao);
            }
        }
        logger.debug("Atendimento finalizado para solicitação: {}", solicitacao);
    }

    public void adicionarTimeAtendimento(TimeAtendimento timeAtendimento) {
        timesAtendimento.put(timeAtendimento.getTipo().getValor(), timeAtendimento);
        logger.debug("Time de atendimento adicionado: {}", timeAtendimento.getTipo());
    }
}
