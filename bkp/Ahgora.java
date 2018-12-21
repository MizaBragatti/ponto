package steps;

import java.util.Calendar;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PageObject;

public class Ahgora{
	PageObject poAhgora = new PageObject();
	@Given("^Eu acesse a pagina de login e preencha os campos de \"([^\"]*)\" e \"([^\"]*)\"$")
	public void eu_acesse_a_pagina_de_login(String matricula, String senha) throws Throwable {
		poAhgora.abreChrome("https://www.ahgora.com.br/externo/index/a142674");
		poAhgora.preencheMatricula(matricula);
		poAhgora.preencheSenhaAhgora(senha);
	}

	@When("^Eu clicar no botao para entrar$")
	public void eu_clicar_no_botao_para_entrar() throws Throwable {
		poAhgora.botaoEntrar();
	}
	
	@Then("^Eu valido o \"([^\"]*)\" e verifico se o login foi efetuado com sucesso$")
	public void eu_valido_se_o_login_foi_efetuado_com_sucesso(String nome) throws Throwable {
	
		if(poAhgora.recuperaTextoAhgora().equals(nome)) 
			System.out.println("Login no AhGora efetuado com sucesso");
		else
			System.out.println("Ops! Login AhGora não efetuado");

	}
		
	@And("^Clicar em espelho do ponto$")
	public void eu_clicar_no_espelho() throws Throwable {
		poAhgora.botaoEspelhoPonto();
	}
	
	@And("^Copiar horas do dia anterior$")
	public void copiar_horas() throws Throwable {
		System.out.println(poAhgora.copiaHoras());
	}
}
