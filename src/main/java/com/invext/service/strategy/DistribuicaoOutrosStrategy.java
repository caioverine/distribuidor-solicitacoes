package com.invext.service.strategy;

import com.invext.model.Solicitacao;
import com.invext.queue.AtendimentoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistribuicaoOutrosStrategy implements DistribuicaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(DistribuicaoOutrosStrategy.class);

    @Autowired
    private AtendimentoQueue atendimentoQueue;

    @Override
    public void distribuir(Solicitacao solicitacao) {
        logger.debug("Distribuindo solicitação de tipo outros: {}", solicitacao.getId());
        atendimentoQueue.adicionarSolicitacao("outros", solicitacao);
        logger.debug("Solicitação de tipo outros distribuída: {}", solicitacao.getId());
    }
}
