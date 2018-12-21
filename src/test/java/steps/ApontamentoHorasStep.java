package steps;

import java.util.Calendar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.AhgoraPage;
import pages.ClarizenPage;

/**
 * @Descricao Classe Step para acessar e recuperar os elementos das páginas do Ahgora e Clarizen 
 * recuperando as horas feitas e registrando-as.
 * @author mizael.bragatti
 *
 */
public class ApontamentoHorasStep{
	WebDriver driver;
	AhgoraPage ahgoraPage;
	ClarizenPage clarizenPage;
	long tempo = 2000;
	String horas=null;
	
	/**
	 * @Descricao Método para acessar a página do Ahgora e preencher os campos 
	 * de matricula e senha
	 * @param matricula
	 * @param senha
	 * @throws Throwable
	 */
	@Given("^Eu acesse a pagina de login e preencha os campos de \"([^\"]*)\" e \"([^\"]*)\"$")
	public void eu_acesse_a_pagina_de_login(String matricula, String senha) throws Throwable {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		ahgoraPage = new AhgoraPage(driver);
		ahgoraPage.navegarParaAhgora();
		ahgoraPage.preencheMatricula(matricula);
		ahgoraPage.preencheSenha(senha);
	}
	
	/**
	 * @Descricao Método para clicar no botao Entrar 
	 * @throws Throwable
	 */
	@When("^Eu clicar no botao para entrar$")
	public void eu_clicar_no_botao_para_entrar() throws Throwable {

		ahgoraPage.botaoEntrar();
		
	}
	
	/**
	 * @Descricao Método para validar se login foi efetuado com sucesso
	 * @param nome
	 * @throws Throwable
	 */
	@Then("^Eu valido o \"([^\"]*)\" e verifico se o login foi efetuado com sucesso$")
	public void eu_valido_se_o_login_foi_efetuado_com_sucesso(String nome) throws Throwable {

		String texto = ahgoraPage.recuperaTextoAhgora(nome);
		if(texto.equals(nome))
			System.out.println("Login no AhGora efetuado com sucesso");
		else
			System.out.println("Ops! Login AhGora não efetuado");
	}
	
	/**
	 * @Descricao Método para clicar no espelho do ponto
	 * @throws Throwable
	 */
	@And("^Clicar em espelho do ponto$")
	public void eu_clicar_no_espelho() throws Throwable {

		ahgoraPage.clicaEspelhoPonto();
		
	}
	
	/**
	 * @Descricao Método para copiar as horas feitas na página do Ahgora
	 * @throws Throwable
	 */
	@And("^Copiar horas do dia anterior$")
	public void copiar_horas() throws Throwable {

		Calendar calendar = Calendar.getInstance();
		int diaDoMes = calendar.get(Calendar.DAY_OF_MONTH);
		int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);

		int dia = diaDoMes - 1;
		
		if (diaDaSemana == 2) {
			dia = diaDoMes - 3;
		}

		horas = ahgoraPage.recuperaHoras(dia);
		
		System.out.println("Quantidade de horas: " + horas);
	}
	
	/**
	 * @Descricao Método para acessar a página do Clarizen e preencher os campos 
	 * de email e senha
	 * @param email
	 * @param senha
	 * @throws Throwable
	 */
	@Given("^Eu acesse a pagina do clarizen e preencha os campos de \"([^\"]*)\" e \"([^\"]*)\"$")
	public void eu_acesse_a_pagina_do_Clarizen(String email, String senha) throws Throwable {
		
		clarizenPage = new ClarizenPage(driver);
		clarizenPage.navegarParaClarizen();
		clarizenPage.preencheEmail(email);
		clarizenPage.preencheSenha(senha);
	}

	/**
	 * @Descricao Método para clicar no botão de login
	 * @throws Throwable
	 */
	@When("^Eu clicar no botao de login$")
	public void eu_clicar_no_botao_de_Login() throws Throwable {

		clarizenPage.botaoLogin();
		
	}
	
	/**
	 * @Descricao Método para validar se login foi efetuado com sucesso
	 * @param nome
	 * @throws Throwable
	 */
	@Then("^Eu valido o \\\"([^\\\"]*)\\\" e verifico se login foi efetuado com sucesso no clarizen$")
	public void eu_valido_se_o_login_foi_efetuado_com_sucesso_no_Clarizen(String nome) throws Throwable {
		
		if(clarizenPage.recuperaTextoClarizen().equals(nome))
			System.out.println("Login no Clarizen efetuado com sucesso");
		else
			System.out.println("Ops! Login Clarizen não efetuado");
	}
	
	/**
	 * @Descricao Método para validar em qual dia da semana está o combo: 
	 * Se está selecionado "Semana Passada" / "Semana Atual"
	 * @throws Throwable
	 */
	@And("^Verificar qual o dia da semana$")
	public void verifica_dia_da_semana() throws Throwable {
		Calendar c = Calendar.getInstance();
		int diaDaSemana = c.get(Calendar.DAY_OF_WEEK);
	
		// verifica se é segunda-feira
		if (diaDaSemana == 2) {
			//verifica se combo está selecionado com "Esta semana"
			String texto = clarizenPage.recuperaTextoCombo();
			if(texto.equals("Esta semana")) {

				clarizenPage.clicaSetaEsquerda();
			}
		}	
	}
	
	/**
	 * @Descricao Método para preencher no Clarizen as horas feitas recuperadas do Ahgora
	 * @throws Throwable
	 */
	@And("^Preencher as horas trabalhadas$")
	public void preencher_horas() throws Throwable {
		
		Calendar calendar = Calendar.getInstance();
		
		int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);

		clarizenPage.clicaCampoHoras(diaDaSemana);
		clarizenPage.clicaCampoHoras(diaDaSemana);
		clarizenPage.preencheHoras(horas);
		clarizenPage.teclaEnter();
		
		System.out.println("Horas incluidas : "+ horas );
		clarizenPage.fecharBrowser();
	}
}
