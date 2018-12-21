package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author mizael.bragatti
 * @Descricao Classe que faz os mapeamentos dos elementos da página do Clarizen
 */
public class ClarizenPage extends AbstractPage{
	WebDriverWait wait = new WebDriverWait(driver, 5000);
	/**
	 * @param driver
	 * @Descricao Construtor da classe que faz os mapeamentos dos elementos 
	 * da página do Clarizen
	 */
	public ClarizenPage(WebDriver driver) {
		super(driver);
	}
	
	/**
	 * @Descricao Método para acessar a página do Clarizen
	 */
	public void navegarParaClarizen() {
		driver.navigate().to("https://app2.clarizen.com/Clarizen/TeamSpace/Timesheet");
	}
	
	/**
	 * @param email
	 * @Descricao Método para preencher o campo email
	 */
	public void preencheEmail(String email) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtLogin")));
		driver.findElement(By.id("txtLogin")).sendKeys(email);
	}
	
	/**
	 * @param senha
	 * @Descricao Método para preencher o campo email
	 */
	public void preencheSenha(String senha) {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("txtPassword")));
		driver.findElement(By.id("txtPassword")).sendKeys(senha);
	}
	
	/**
	 * @Descricao Método para clicar no botão para efetuar o login
	 */
	public void botaoLogin() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("lbtLogin")));
		driver.findElement(By.id("lbtLogin")).click();
	}
	
	/**
	 * @return texto com o nome do usuário
	 * @Descricao Método para recuperar texto com o nome do usuário 
	 */
	public String recuperaTextoClarizen() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"userMenu\"]/li/a/span")));
		return driver.findElement(By.xpath("//*[@id=\"userMenu\"]/li/a/span")).getText();
	}
	
	/**
	 * @return texto do combo para verificar se está na semana atual / passada
	 * @Descricao Método para recuperar texto do combo informando qual semana está 
	 */
	public String recuperaTextoCombo() {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#TimeFilter_dd > li > a")));
		return driver.findElement(By.cssSelector("#TimeFilter_dd > li > a")).getText();
	}
	
	/**
	 * @Descricao Método para clicar na seta para esquerda do controle de semanas
	 */
	public void clicaSetaEsquerda() {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#TimeFilter > a.time-filter-prev-snapshot")));
		driver.findElement(By.cssSelector("#TimeFilter > a.time-filter-prev-snapshot")).click();
	}
	
	/**
	 * @param diaDaSemana
	 * @Descricao Clica no campo para apontar as horas 
	 */
	public void clicaCampoHoras(int diaDaSemana) {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='row-2']/td["+ (diaDaSemana - 1) +"]/span[2]/div/div[1]")));
		driver.findElement(By.xpath("//*[@id='row-2']/td["+ (diaDaSemana - 1) +"]/span[2]/div/div[1]")).click();
	}
	
	/**
	 * @param horas
	 * @Descricao Preenche o campo com as horas
	 */
	public void preencheHoras(String horas){
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id,'inputDuration')]")));
		driver.findElement(By.xpath("//input[contains(@id,'inputDuration')]")).sendKeys(horas);
	}
	
	/**
	 * @Descricao tecla o enter 
	 */
	public void teclaEnter(){
		driver.findElement(By.xpath("//input[contains(@id,'inputDuration')]")).sendKeys(Keys.ENTER);
	}
}
