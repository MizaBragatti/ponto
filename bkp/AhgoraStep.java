package steps;

import java.util.Calendar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AhgoraPage;


public class AhgoraStep{
	WebDriver driver;
	AhgoraPage ahgoraPage;
	long tempo = 2000;
	
	@Given("^Eu acesse a pagina de login e preencha os campos de \"([^\"]*)\" e \"([^\"]*)\"$")
	public void eu_acesse_a_pagina_de_login(String matricula, String senha) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		ahgoraPage = new AhgoraPage(driver);
		ahgoraPage.navegarParaAhgora();
		
		Thread.sleep(tempo);
		ahgoraPage.preencheMatricula(matricula);
		
		Thread.sleep(tempo);
		ahgoraPage.preencheSenha(senha);
	}

	@When("^Eu clicar no botao para entrar$")
	public void eu_clicar_no_botao_para_entrar() throws Throwable {
		Thread.sleep(tempo);
		ahgoraPage.botaoEntrar();
		
	}
	
	@Then("^Eu valido o \"([^\"]*)\" e verifico se o login foi efetuado com sucesso$")
	public void eu_valido_se_o_login_foi_efetuado_com_sucesso(String nome) throws Throwable {
		Thread.sleep(tempo);
		ahgoraPage.recuperaTextoAhgora(nome);
		
		if(ahgoraPage.texto.equals(nome))
			System.out.println("Login no AhGora efetuado com sucesso");
		else
			System.out.println("Ops! Login AhGora não efetuado");
	}
		
	@And("^Clicar em espelho do ponto$")
	public void eu_clicar_no_espelho() throws Throwable {
		Thread.sleep(tempo);
		ahgoraPage.clicaEspelhoPonto();
	}
	
	@And("^Copiar horas do dia anterior$")
	public void copiar_horas() throws Throwable {

		Calendar calendar = Calendar.getInstance();
		int diaDoMes = calendar.get(Calendar.DAY_OF_MONTH);
		int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);

		int dia = diaDoMes - 1;
		
		if (diaDaSemana == 2) {
			dia = diaDoMes - 3;
		}
		Thread.sleep(tempo);
		ahgoraPage.recuperaHoras(dia);
		
		System.out.println(ahgoraPage.horas);
	}
}
