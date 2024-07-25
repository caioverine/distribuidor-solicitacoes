package com.invext.controller;

import com.invext.model.Solicitacao;

import com.invext.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @PostMapping
    public ResponseEntity<Void> distribuirSolicitacao(@RequestBody Solicitacao solicitacao) {
        solicitacaoService.distribuirSolicitacao(solicitacao);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/processar")
    public ResponseEntity<Solicitacao> processarSolicitacao(@RequestParam String tipo) {
        Solicitacao solicitacao = solicitacaoService.processarSolicitacao(tipo);
        return ResponseEntity.ok(solicitacao);
    }
}
