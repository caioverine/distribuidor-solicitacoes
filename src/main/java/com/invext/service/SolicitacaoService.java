package com.invext.service;

import com.invext.model.Atendente;
import com.invext.model.Solicitacao;
import com.invext.model.TimeAtendimento;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SolicitacaoService {

    private Map<String, TimeAtendimento> times;

    public SolicitacaoService(){
        times = new HashMap<>();
        times.put("Cartões", new TimeAtendimento("Cartões"));
        times.put("Empréstimos", new TimeAtendimento("Empréstimos"));
        times.put("Outros Assuntos", new TimeAtendimento("Outros Assuntos"));

        adicionarAtendente("Cartões", new Atendente("A1"));
        adicionarAtendente("Empréstimos", new Atendente("A2"));
        adicionarAtendente("Outros Assuntos", new Atendente("A3"));
    }

    private void adicionarAtendente(String time, Atendente atendente) {
        times.get(time).adicionarAtendente(atendente);
    }

    public void distribuirSolicitacao(Solicitacao solicitacao) {
        System.out.println(times.get("Cartões").getStatus());
        String tipo = solicitacao.getTipo();
        switch (tipo) {
            case "Problemas com cartão":
                times.get("Cartões").adicionarSolicitacao(solicitacao);
                break;
            case "Contratação de empréstimo":
                times.get("Empréstimos").adicionarSolicitacao(solicitacao);
                break;
            default:
                times.get("Outros Assuntos").adicionarSolicitacao(solicitacao);
                break;
        }
    }
}
