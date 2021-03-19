# Automação de testes prova técnica API

Este é o projeto de automação de testes da prova técnica API.

### Pre-Requisitos
* Java 8+ JDK deve estar instalado;
* Maven deve estar instalado e configurado no path da aplicação;
---

## Inicializando a aplicação

1. Copie o projeto 'prova-tecnica-api-test' para um diretório a sua escolha;
1. Anote o caminho do diretório
1. Abra o arquivo local `bootapp.sh` e ajuste o caminho/diretório da aplicação


### Como executar o `bootapp.sh`:
1. Procure pelo arquivo `bootapp.sh` em sua IDE
1. Clique com o botão direito e depois em executar("Run")

**Modo alternativo:** 
1. Abra o terminal/console e navegue ate o diretório do projeto atual
1. Execute os seguintes commandos:

```bash
./bootapp.sh 
```

**OBS**: caso ocorra com a mensagem `zsh: permission denied: ./` execute o seguinte comando: `chmod +x ./bootapp.sh`

Pronto, a aplicação será iniciada a partir do JAR executável criado.

---
## Executando os Testes

1. Abra um terminal/console e execute o seguinte comando:
`mvn clean test --log-file testes-resultado.txt`

1. Consulte o arquivo gerado `tests-resultado.txt` para verificar detalhes dos logs

---
## Relatório de Testes

Após a execução dos testes abra um terminal/console e execute o seguinte comando:
`allure serve allure-results`

O report irá abrir no browser com o resultado dos testes.