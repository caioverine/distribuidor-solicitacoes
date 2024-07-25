package com.invext.service;

import com.invext.model.Solicitacao;
import com.invext.queue.AtendimentoQueue;
import com.invext.service.strategy.DistribuicaoStrategy;
import com.invext.service.strategy.factory.DistribuicaoStrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {

    private static final Logger logger = LoggerFactory.getLogger(SolicitacaoService.class);

    @Autowired
    private AtendimentoQueue atendimentoQueue;

    @Autowired
    private DistribuicaoStrategyFactory strategyFactory;

    public void distribuirSolicitacao(Solicitacao solicitacao) {
        DistribuicaoStrategy strategy = strategyFactory.getStrategy(solicitacao.getTipo());
        logger.debug("Distribuindo solicitação: {}", solicitacao.getId());
        strategy.distribuir(solicitacao);
        logger.debug("Solicitação distribuída: {}", solicitacao.getId());
    }

    public Solicitacao processarSolicitacao(String tipo) {
        logger.debug("Processando solicitação do tipo: {}", tipo);
        Solicitacao solicitacao = atendimentoQueue.processarSolicitacao(tipo);
        logger.debug("Solicitação processada: {}", solicitacao);
        return solicitacao;
    }
}
