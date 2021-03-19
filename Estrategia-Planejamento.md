#Estratégia

Nesse projeto vamos realizar a validação da simulaçõa de crédito da API REST do Sicredi.

Atuaremos nos testes de integração da piramide de testes, assim conseguimos adiantar os testes enquanto o front está em construçõa.

##Porque projeto separado e testando contra um executável?

As ferramentas escolhidas para os testes foram:

* Java: Uma das linguagens de programação mais utilizadas no mundo. 
  
* RestAssured: É uma ferramenta que foi desenvolvida para facilitar a criação de testes automatizados para APIs REST. Esta oferece suporte para validar protocolo HTTP e requisições em JSON.
O Rest Assured oferece também extensas opções de validações das requisições que são enviadas nos serviços REST, tais como: Status Code, Headers e também elementos do Body. Tornando a ferramenta extremamente flexível para utilizar na criação de testes automatizados de API.
  
* TestNG: Framework para realização de testes.

* Allure Report: Framework para gerar status report.

#Planejamento
Vamos realizar testes funcionais API.
Os testes não funcionais como segurança, carga, stress, foram considerados fora do escopo visto que não existem requisitos para validarmos os testes.


##Teste funcionais

###Endpoint Restrições
* Validar status 204 para um CPF sem restrição
* Validar status 200 para um CPF sem restrição
* Validar para validar CPF inválido
* Validar para validar CPF não enviado

###Endpoint Simulações
POST:
* Validar status 201 de inserção de simulação com sucesso
* Validar status 400 atributos obrigatórios 
* Validar status 400 regra dos atributos
* Validar status 409 para CPF duplicado
* Validar status 400 de falta de informações

PUT:
* Validar alteração de simulação
* Validar status 404 de CPF não encontrado
* Validar status 409 CPF alterado já existente

GET:
* Validar retorno de simulações cadastradas
* Validar status 204 por não ter simulações cadastradas
* Validar retorno de simulação por CPF
* Validar status 404 por CPF não ter simulação cadastrada

DELETE:
* Validar remoção de simulação cadastrada
* Validar status 404 por não encontrar simulação para remover