package steps;

import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.ClarizenPage;

public class ClarizenStep{
	WebDriver driver;
	ClarizenPage clarizenPage;
	
	long tempo = 2000;
	
	@Given("^Eu acesse a pagina do clarizen e preencha os campos de \"([^\"]*)\" e \"([^\"]*)\"$")
	public void eu_acesse_a_pagina_do_Clarizen(String email, String senha) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		clarizenPage = new ClarizenPage(driver);
		clarizenPage.navegarParaClarizen();
		
		Thread.sleep(tempo);
		clarizenPage.preencheEmail(email);
		
		Thread.sleep(tempo);
		clarizenPage.preencheSenha(senha);
	}

	@When("^Eu clicar no botao de login$")
	public void eu_clicar_no_botao_de_Login() throws Throwable {
		Thread.sleep(tempo);
		clarizenPage.botaoLogin();
		
	}
	
	@Then("^Eu valido o \\\"([^\\\"]*)\\\" e verifico se login foi efetuado com sucesso no clarizen$")
	public void eu_valido_se_o_login_foi_efetuado_com_sucesso_no_Clarizen(String nome) throws Throwable {
		Thread.sleep(tempo);
		clarizenPage.recuperaTextoClarizen();
		
		if(clarizenPage.texto.equals(nome))
			System.out.println("Login no Clarizen efetuado com sucesso");
		else
			System.out.println("Ops! Login AhGora não efetuado");
	}
		
	@And("^Verificar qual o dia da semana$")
	public void verifica_dia_da_semana() throws Throwable {
		Calendar c = Calendar.getInstance();
		int diaDaSemana = c.get(Calendar.DAY_OF_WEEK);
	
		// verifica se é segunda-feira
		if (diaDaSemana == 2) {
			//verifica se combo está selecionado com "Esta semana"
			clarizenPage.recuperaTextoCombo();
			if(clarizenPage.textoCombo.equals("Esta semana")) {
				Thread.sleep(tempo);
				clarizenPage.clicaSetaEsquerda();
			}
		}	
	}
	
	@And("^Preencher as horas trabalhadas$")
	public void preencher_horas() throws Throwable {
		
		Calendar calendar = Calendar.getInstance();
		
		int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);

		Thread.sleep(tempo);
		clarizenPage.clicaCampoHoras(diaDaSemana);
		
		Thread.sleep(tempo);
		clarizenPage.clicaCampoHoras(diaDaSemana);
		
		Thread.sleep(tempo);

		clarizenPage.registraHoras();
		System.out.println("Horas incluidas");
	}
}
