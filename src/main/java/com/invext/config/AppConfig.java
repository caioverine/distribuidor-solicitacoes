package com.invext.config;

import com.invext.model.Atendente;
import com.invext.model.TimeAtendimento;
import com.invext.model.TipoTimeAtendimentoEnum;
import com.invext.queue.AtendimentoQueue;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Autowired
    private AtendimentoQueue atendimentoQueue;

    @PostConstruct
    public void initializeTimesAndAtendentes() {
        // Inicializa Time de Atendimento Cartões
        TimeAtendimento timeCartoes = new TimeAtendimento(TipoTimeAtendimentoEnum.CARTOES);
        for (int i = 1; i <= 2; i++) {
            timeCartoes.adicionarAtendente(new Atendente("AtendenteCartoes-" + i));
        }
        atendimentoQueue.adicionarTimeAtendimento(timeCartoes);

        // Inicializa Time de Atendimento Empréstimos
        TimeAtendimento timeEmprestimos = new TimeAtendimento(TipoTimeAtendimentoEnum.EMPRESTIMOS);
        for (int i = 1; i <= 1; i++) {
            timeEmprestimos.adicionarAtendente(new Atendente("AtendenteEmprestimos-" + i));
        }
        atendimentoQueue.adicionarTimeAtendimento(timeEmprestimos);

        // Inicializa Time de Atendimento Outros Assuntos
        TimeAtendimento timeOutros = new TimeAtendimento(TipoTimeAtendimentoEnum.OUTROS);
        for (int i = 1; i <= 3; i++) {
            timeOutros.adicionarAtendente(new Atendente("AtendenteOutros-" + i));
        }
        atendimentoQueue.adicionarTimeAtendimento(timeOutros);
    }
}
