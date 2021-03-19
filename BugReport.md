# Bug Report
Documento com relatório de bugs encontrados na API

##Endpoint Simulações
1. **API permitindo cadastrar simulação com valores menores de 1000,00** 
  
**Descrição:** Ao enviar um valor de 999, está sendo possível cadastrar um simulação 
      
**Evidência**:
      
HTTP/1.1 201
      
Content-Type: application/json;charset=UTF-8
      
Transfer-Encoding: chunked
 
Date: Wed, 17 Mar 2021 20:44:10 GMT

{
"id": 78,
"nome": "Atila",
"cpf": "12123434567",
"email": "atila@ibest.com",
"valor": 999,
"parcelas": 2,
"seguro": true
}

===============================================
Default Suite
Total tests run: 1, Passes: 1, Failures: 0, Skips: 0
===============================================


Process finished with exit code 0

---

2. **Retorno de CPF duplicado com status 400**

**Descrição:** Ao tentar cadastrar uma nova simulação com um CPF já cadastrado, a API retorna um status 400, segundo a documentação, o status deveria ser 409.

**Evidência:**

HTTP/1.1 400

Content-Type: application/json;charset=UTF-8

Transfer-Encoding: chunked

Date: Wed, 17 Mar 2021 20:53:03 GMT

Connection: close

{
"mensagem": "CPF duplicado"
}
---

3. **API não alterando valor de simulação**

**Descrição:** Ao realizar uma alteração de uma simulação, o valor não está sendo alterado.

**Evidência:**

HTTP/1.1 200

Content-Type: application/json;charset=UTF-8

Transfer-Encoding: chunked

Date: Wed, 17 Mar 2021 21:41:27 GMT

{
"id": 11,
"nome": "Cassel",
"cpf": "66414919004",
"email": "cassel@ibest.com",
"valor": 11000.00,
"parcelas": 5,
"seguro": false
}

java.lang.AssertionError: 1 expectation failed.

JSON path valor doesn't match.

Expected: is "2000"

Actual: <11000.0F>

---
4. **Status errado de CPF já existente**

**Descrição:** Ao realizar uma alteração de uma simulação utilizando um CPF já existe, a API informa o status 400, o correto seria 404.

**Evidência:**

HTTP/1.1 400

Content-Type: application/json;charset=UTF-8

Transfer-Encoding: chunked

Date: Wed, 17 Mar 2021 21:48:05 GMT

Connection: close

{
"mensagem": "CPF duplicado"
}

---
5. **Divergências de informações método GET simulações

**Descrição:** No arquivo "readme" da aplicação diz que quando não temos simulações cadastradas o retorno deve ser um Status 204, na documentação do Swagger da API fala que será um 404, e ao realizar o teste o retorno é um 200. Qual correto?

**Evidência:**

HTTP/1.1 200

Content-Type: application/json;charset=UTF-8

Transfer-Encoding: chunked

Date: Thu, 18 Mar 2021 12:20:47 GMT

[

]

---
6. **Erro método DELETE**

**Descrição:** No arquivo "readme" da aplicação diz que ao remover uma simulação com sucesso temos o status 204 de retorno, mas o que temos é o status 200. Também temos a informação que ao tentarmos remover uma simulação que não existe, temos o status 404, mas o retorno está sendo também do status 200.

**Evidência:**

java.lang.AssertionError: 1 expectation failed.

Expected status code <204> but was <200>.


java.lang.AssertionError: 1 expectation failed.

Expected status code <404> but was <200>.