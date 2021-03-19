package main.java;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RestricoesTest extends BaseTest {
    @Feature("Consulta Restrições")

    @DataProvider(name = "validaCPF")
    public Object[][] dataProviderMethod() {
        return new Object[][] {
                { "97093236014", 200},
                { "12345678901", 204},
                { "xxxxxxxx", 204},
                { "", 404} };
    }

    @Test (dataProvider = "validaCPF")
    @Story("Validar restrições por CPF")
    public void retornaRestricaoCpf(String cpf, int status) {
        prepararRequest().get(PATH_RESTRICOES + cpf)
                .then()
                .statusCode(status);
    }
}
