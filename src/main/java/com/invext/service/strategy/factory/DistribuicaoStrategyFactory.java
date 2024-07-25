package com.invext.service.strategy.factory;

import com.invext.service.strategy.DistribuicaoCartaoStrategy;
import com.invext.service.strategy.DistribuicaoEmprestimoStrategy;
import com.invext.service.strategy.DistribuicaoOutrosStrategy;
import com.invext.service.strategy.DistribuicaoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DistribuicaoStrategyFactory {

    private final Map<String, DistribuicaoStrategy> strategies = new HashMap<>();

    @Autowired
    public DistribuicaoStrategyFactory(DistribuicaoCartaoStrategy cartaoStrategy,
                                       DistribuicaoEmprestimoStrategy emprestimoStrategy,
                                       DistribuicaoOutrosStrategy outrosStrategy) {
        strategies.put("problemas com cartão", cartaoStrategy);
        strategies.put("contratação de empréstimo", emprestimoStrategy);
        strategies.put("outros", outrosStrategy);
    }

    public DistribuicaoStrategy getStrategy(String tipo) {
        return strategies.getOrDefault(tipo.toLowerCase(), strategies.get("default"));
    }
}
