# Sistema de Distribuição de Solicitações Invext

Este projeto é um sistema de distribuição de solicitações para a Invext. O sistema distribui solicitações de clientes para o time de atendimento apropriado com base no tipo de solicitação. Existem três tipos de solicitações: problemas com cartão, pedidos de empréstimo e outros assuntos.


## Funcionalidades

Distribui solicitações para o time apropriado com base no tipo de solicitação.
Cada time pode atender um máximo de 3 solicitações simultâneas por atendente.
Se todos os atendentes de um time estiverem ocupados, as solicitações são enfileiradas e processadas assim que um atendente estiver disponível.


## Instalação
1. Clone o repositório
    git clone https://github.com/caioverine/distribuidor-solicitacoes.git

2. Construa o projeto no Maven
    mvn clean install

3. Execute a aplicação
    mvn spring-boot:run

A aplicação será iniciada em http://localhost:8080.


## Testando com Curl
Você pode testar a API usando curl ou qualquer outra ferramenta de teste de API.

Exemplo de Requisição
curl -X POST http://localhost:8080/api/solicitacoes \
     -H "Content-Type: application/json" \
     -d '{"id": "S1", "tipo": "Problemas com cartão"}'


## Endpoints
- POST /api/solicitacoes
  
  Distribui uma solicitação para o time apropriado.
  
  Corpo da Requisição: {"id": "string","tipo": "string"}

- POST /api/solicitacoes/processar?tipo=string
  
  Processa uma solicitação de um determinado tipo pelo atendente responsável.
