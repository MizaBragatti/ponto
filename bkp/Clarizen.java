package steps;

import pages.PageObject;

import java.util.Calendar;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Clarizen{
	
	PageObject poClarizen = new PageObject();
	
	@Given("^Eu acesse a pagina do clarizen e preencha os campos de \"([^\"]*)\" e \"([^\"]*)\"$")
	public void eu_acesse_a_pagina_do_Clarizen(String email, String senha) throws Throwable {
		poClarizen.driver.navigate().to(("https://app2.clarizen.com/Clarizen/TeamSpace/Timesheet"));
		poClarizen.preencheEmailClarizen(email);
		poClarizen.preencheSenhaClarizen(senha);
	}
	
	@When("^Eu clicar no botao de login$")
	public void eu_clicar_no_botao_de_Login() throws Throwable {
		poClarizen.botaoLoginClarizen();
	}
	
	@Then("^Eu valido o \\\"([^\\\"]*)\\\" e verifico se login foi efetuado com sucesso no clarizen$")
	public void eu_valido_se_o_login_foi_efetuado_com_sucesso_no_Clarizen(String nome) throws Throwable {

		if(poClarizen.recuperaTextoClarizen().equals(nome)) 
			System.out.println("Login no Clarizen efetuado com sucesso");
		else
			System.out.println("Ops! Login não efetuado");
		
	}
	
	@And("^Verificar qual o dia da semana$")
	public void verifica_dia_da_semana() throws Throwable {
		
		Calendar c = Calendar.getInstance();
		int diaDaSemana = c.get(Calendar.DAY_OF_WEEK);
	
		// verifica se é segunda-feira
		if (diaDaSemana == 2) {
			//verifica se combo está selecionado com "Esta semana"
			if(poClarizen.recuperaTextoComboClarizen().equals("Esta semana")) {
				poClarizen.clicaSetaEsquerda();
			}
		}

	}
	
	@And("^Preencher as horas trabalhadas$")
	public void preencher_horas() throws Throwable {
		poClarizen.registrarHoras();
		System.out.println("Horas incluidas");
	}

}
