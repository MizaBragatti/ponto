package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * @Descricao Classe de mapeamentos dos elementos da página do AhGora
 * @author mizael.bragatti
 */
public class AhgoraPage extends AbstractPage{
	WebDriverWait wait = new WebDriverWait(driver, 5000);
	
	/**
	 * @Descricao Construtor da Classe de mapeamentos dos elementos da página do AhGora
	 * @param driver
	 */
	public AhgoraPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * @Descricao Método para acessar a página do Ahgora
	 */
	public void navegarParaAhgora() {
		driver.navigate().to("https://www.ahgora.com.br/externo/index/a142674");
	}
	
	/**
	 * @Descricao Método para preencher a matricula
	 * @param matricula
	 */
	public void preencheMatricula(String matricula) {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#login #matricula")));
		driver.findElement(By.cssSelector("#login #matricula")).sendKeys(matricula);
	}
	
	/**
	 * @Descricao Método para preencher a senha
	 * @param senha
	 */
	public void preencheSenha(String senha) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("senha")));
		driver.findElement(By.id("senha")).sendKeys(senha);
	}
	
	/**
	 * @Descricao Método para clicar no botão Entrar
	 */
	public void botaoEntrar() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"boxLogin\"]/div[3]/button")));
		driver.findElement(By.xpath("//*[@id=\"boxLogin\"]/div[3]/button")).click();
	}
	
	/**
	 * @Descricao Método para recuperar texto para validar se o logon foi efetuado
	 * @param nome
	 * @return Texto para validar se o usuário conseguiu logar
	 */
	public String recuperaTextoAhgora(String nome) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[contains(text(), '" + nome + "')]")));
		return driver.findElement(By.xpath("//h2[contains(text(), '" + nome + "')]")).getText();
	}
	
	/**
	 * @Descricao Método para clicar no espelho do ponto
	 */
	public void clicaEspelhoPonto() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"espelho_ponto_icon\"]/span")));
		driver.findElement(By.xpath("//*[@id=\"espelho_ponto_icon\"]/span")).click();
	}
	
	/**
	 * @Descricao Método para recuperar quantidade de horas feitas
	 * @param dia
	 * @return Quantidade de horas feitas 
	 */
	public String recuperaHoras(int dia) {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#content > table:nth-child(5) > tbody > tr:nth-child("+ dia +") > td:nth-child(7)")));
		return driver.findElement(By.cssSelector("#content > table:nth-child(5) > tbody > tr:nth-child("+ dia +") > td:nth-child(7)")).getText().substring(19, 24);
	}
}
