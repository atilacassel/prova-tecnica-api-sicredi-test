package main.java;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class SimulacoesTest extends BaseTest {
    @Feature("Simulações")

    @DataProvider(name = "cadastro")
    public Object[][] dataProviderMethod(){
            return new Object[][] {
                    {"atila@ibest.com", 1500, 2, "true", 201},
                    {"emailerrado", 1500, 2, "true", 400},
                    {"atila@ibest.com", 999, 2, "true", 400},
                    {"atila@ibest.com", 40001, 2, "true", 400},
                    {"atila@ibest.com", 1500, 1, "true", 400},
                    {"atila@ibest.com", 1500, 2, "true", 409}
            };
    }

    @DataProvider(name = "retornaSimulacoes")
    public Object [][] dataProviderMethodSimulacoes() {
        return new Object[][] {
                {"12123434567", 200},
                {"00000000000", 404}
        };
    }

    @DataProvider(name = "removeSimulacoes")
    public Object [][] dataProviderMethodRemoveSimulacoes() {
        return new Object[][] {
                {11, 204},
                {00, 404}
        };
    }

    @Test (dataProvider = "cadastro")
    @Story("Cadastrar simulações")
    public void cadastraSimulacao(String email, int valor, int parcelas, String seguro, int status) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("nome", "Atila");
        requestParams.put("cpf", "12123434567");
        requestParams.put("email", email);
        requestParams.put("valor", valor);
        requestParams.put("parcelas", parcelas);
        requestParams.put("seguro", seguro);

        prepararRequestComBody(requestParams.toJSONString())
                .post(PATH_SIMULACOES)
                .then()
                .log().all()
                .assertThat().statusCode(status);
    }

    @Test
    @Story("Cadastrar simulações sem preenchimento de CPF")
    public void cadastraSimulacãoFaltaInformacaoSemSucesso() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", "13");
        requestParams.put("nome", "Atila");
        requestParams.put("email", "atila@ibest.com");
        requestParams.put("valor", "750");
        requestParams.put("parcelas", "2");
        requestParams.put("seguro", "true");

        prepararRequestComBody(requestParams.toJSONString())
                .post(PATH_SIMULACOES)
                .then()
                .assertThat().statusCode(400);
    }

    @Test
    @Story("Alterar simulações")
    public void alterarSimulacaoComSucesso() {
        String cpf = "66414919004";
        JSONObject requestParams = new JSONObject();
        requestParams.put("nome", "Cassel");
        requestParams.put("email", "cassel@ibest.com");
        requestParams.put("valor", "2000");
        requestParams.put("parcelas", "5");
        requestParams.put("seguro", "false");

        prepararRequestComBody(requestParams.toJSONString())
                .put(PATH_SIMULACOES + cpf)
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .body("nome", is("Cassel"))
                .body("email", is("cassel@ibest.com"))
                .body("valor", is("2000"))
                .body("parcelas", is("5"))
                .body("seguro", is("false"));
    }

    @Test
    @Story("Validar alterar simulação inexistente")
    public void alteraSimulacaoCpfNaoEncontrado() {
        String cpf = "12123434500";
        JSONObject requestParams = new JSONObject();
        requestParams.put("nome", "Cassel");
        requestParams.put("email", "cassel@ibest.com");
        requestParams.put("valor", "850");
        requestParams.put("parcelas", "5");
        requestParams.put("seguro", "false");

        prepararRequestComBody(requestParams.toJSONString())
                .put(PATH_SIMULACOES + cpf)
                .then()
                .log().all()
                .assertThat().statusCode(404)
                .body("mensagem", containsString("CPF 12123434500 não encontrado"));
    }

    @Test
    @Story("Validar alterar simulação informando CPF que já possuí simulação")
    public void alteraSimulacaoCpfEnviadaJaExistenteSemSucesso() {
        String cpf = "12123434567";
        JSONObject requestParams = new JSONObject();
        requestParams.put("nome", "Cassel");
        requestParams.put("cpf", "66414919004");
        requestParams.put("email", "cassel@ibest.com");
        requestParams.put("valor", "850");
        requestParams.put("parcelas", "5");
        requestParams.put("seguro", "false");

        prepararRequestComBody(requestParams.toJSONString())
                .put(PATH_SIMULACOES + cpf)
                .then()
                .log().all()
                .assertThat().statusCode(409)
                .body("mensagem", containsString("CPF duplicado"));
    }

    @Test
    @Story("Pesquisar simulações cadastradas")
    public void retornaSimulacoesComSucesso() {
        prepararRequest()
                .get(PATH_SIMULACOES)
                .then()
                .assertThat().statusCode(200)
                .log().all();
    }

    @Test
    @Story("Validar pesquisar sem simulações cadastradas")
    public void validaStatus204SemSimulacoesCadastradas() {
        prepararRequest().delete(PATH_SIMULACOES + "11");
        prepararRequest().delete(PATH_SIMULACOES + "12");
        prepararRequest()
                .get(PATH_SIMULACOES)
                .then()
                .log().all()
                .assertThat().statusCode(204);
    }

    @Test (dataProvider = "retornaSimulacoes")
    @Story("Pesquisar simulação por CPF")
    public void retornaSimulacaoPorCpf(String cpf, int status) {
        prepararRequest().get(PATH_SIMULACOES + cpf)
                .then()
                .assertThat().statusCode(status);
    }

    @Test (dataProvider = "removeSimulacoes")
    @Story("Excluir simulações")
    public void removeSimulacaoComSucesso(int id, int status) {
        prepararRequest().delete(PATH_SIMULACOES + id)
                .then()
                .assertThat().statusCode(status)
                .log().all();
    }
}
