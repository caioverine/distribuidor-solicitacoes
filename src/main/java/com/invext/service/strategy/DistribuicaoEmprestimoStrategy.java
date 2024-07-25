package com.invext.service.strategy;

import com.invext.model.Solicitacao;
import com.invext.queue.AtendimentoQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistribuicaoEmprestimoStrategy implements DistribuicaoStrategy {

    private static final Logger logger = LoggerFactory.getLogger(DistribuicaoEmprestimoStrategy.class);

    @Autowired
    private AtendimentoQueue atendimentoQueue;

    @Override
    public void distribuir(Solicitacao solicitacao) {
        logger.debug("Distribuindo solicitação de empréstimo: {}", solicitacao.getId());
        atendimentoQueue.adicionarSolicitacao("emprestimos", solicitacao);
        logger.debug("Solicitação de empréstimo distribuída: {}", solicitacao.getId());
    }
}
