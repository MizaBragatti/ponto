package pages;

import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {
	
	public WebDriver driver;
	public WebElement element;
	long tempo = 1000;
	String qtdHoras="";
	
	int diaDoMes;
	Calendar c = Calendar.getInstance();
	int diaDaSemana = c.get(Calendar.DAY_OF_WEEK);
	
	
	public PageObject(){
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public void abreChrome(String pagina) throws InterruptedException{
		Thread.sleep(tempo);
		driver.get(pagina);
	}

	public void preencheMatricula(String matricula) throws InterruptedException {
		Thread.sleep(tempo);
		driver.findElement(By.cssSelector("#login #matricula")).sendKeys(matricula);
	}
	
	public void preencheSenhaAhgora(String senha) throws InterruptedException {
		Thread.sleep(tempo);
		driver.findElement(By.id("senha")).sendKeys(senha);
	}
	
	public void preencheEmailClarizen(String email) throws InterruptedException {
		Thread.sleep(tempo);
		driver.findElement(By.id("txtLogin")).sendKeys(email);
	}
	
	public void preencheSenhaClarizen(String senha) throws InterruptedException {
		Thread.sleep(tempo);
		driver.findElement(By.id("txtPassword")).sendKeys(senha);
	}
	
	public void botaoEntrar() throws InterruptedException {
		Thread.sleep(tempo);
		driver.findElement(By.xpath("//*[@id=\"boxLogin\"]/div[3]/button")).click();
	}
	
	public void botaoLoginClarizen() throws InterruptedException {
		Thread.sleep(tempo);
		driver.findElement(By.id("lbtLogin")).click();
	}
	
	public String recuperaTextoAhgora() throws InterruptedException {
		Thread.sleep(tempo);
		return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/h2")).getText();
	}
	
	public String recuperaTextoClarizen() throws InterruptedException {
		Thread.sleep(tempo);
		return driver.findElement(By.xpath("//*[@id=\"userMenu\"]/li/a/span")).getText();
	}
	
	public String recuperaTextoComboClarizen() throws InterruptedException {
		Thread.sleep(tempo);
		return driver.findElement(By.cssSelector("#TimeFilter_dd > li > a")).getText();
	}
	
	public void botaoEspelhoPonto() throws InterruptedException {
		Thread.sleep(tempo);
		driver.findElement(By.xpath("//*[@id=\"espelho_ponto_icon\"]/span")).click();
	}
	
	
	public String copiaHoras() throws InterruptedException {
		
		diaDoMes = c.get(Calendar.DAY_OF_MONTH);

		int dia = diaDoMes - 1;
		
		if (diaDaSemana == 2) {
			dia = diaDoMes - 3;
		}
		
		Thread.sleep(tempo);
		this.qtdHoras = driver.findElement(By.cssSelector("#content > table:nth-child(5) > tbody > tr:nth-child("+ dia +") > td:nth-child(7)")).getText().substring(19, 24);
		
		//setHoras(qtdHoras);
		return this.qtdHoras;
		/*
		String data = chrome.formCss("#content > table:nth-child(5) > tbody > tr:nth-child("+dia+") > td:nth-child(1)").getText();
		String descritivo = chrome.formCss("#content > table:nth-child(5) > tbody > tr:nth-child("+dia+") > td:nth-child(2)").getText();
		String horarios = chrome.formCss("#content > table:nth-child(5) > tbody > tr:nth-child("+dia+") > td:nth-child(3)").getText();
		*/
		//String textoFinal = dia +" "+ data + "\t" + descritivo + "\t" + horarios;
	}
	
	public void registrarHoras() throws InterruptedException {

		if(diaDaSemana == 2) {
			driver.findElement(By.xpath("//*[@id=\"row-2\"]/td["+ 6 +"]/span[2]")).click();
			driver.findElement(By.xpath("//*[@id=\"row-2\"]/td["+ 6 +"]/span[2]")).sendKeys(this.qtdHoras);
			
		}
		else{
		
			driver.findElement(By.xpath("//*[@id='row-2']/td["+ (diaDaSemana - 1) +"]/span[2]/div/div[1]")).click();
			driver.findElement(By.xpath("//*[@id='row-2']/td["+ (diaDaSemana - 1) +"]/span[2]/div/div[1]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[contains(@id,'inputDuration')]")).sendKeys(Keys.chord(this.qtdHoras,Keys.ENTER));
		}
		
	}
	
	public void clicaSetaEsquerda() throws InterruptedException {
		Thread.sleep(tempo);
		driver.findElement(By.cssSelector("#TimeFilter > a.time-filter-prev-snapshot")).click();
	}
	
	public void encerrarNavegador() {
		driver.quit();
	}
}
