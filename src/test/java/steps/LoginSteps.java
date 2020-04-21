package steps;

import br.com.data.DataYaml;
import br.com.driver.DriverManager;
import br.com.pages.HomePage;
import br.com.pages.LoginPage;
import br.com.report.Report;
import cucumber.api.java.AfterStep;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class LoginSteps {

    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();


    @Dado("eu estou na pagina de login")
    public void eu_estou_na_pagina_de_login() {

        loginPage.acessaAplicacao();

    }

    @Quando("eu efetuar o login com credencias validas")
    public void eu_efetuar_o_login_com_credencias_validas() {
        loginPage.executaLogin(DataYaml.getMapYamlValues("Usuarios","credenciais_validas"));
    }

    @Entao("sera apresentado a tela do menu principal")
    public void sera_apresentado_a_tela_do_menu_principal() {
        homePage.validaAcesso();
    }
}
