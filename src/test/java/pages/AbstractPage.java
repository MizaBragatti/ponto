package pages;

import org.openqa.selenium.WebDriver;
/**
 * @Descricao Classe Abstrata para compartilhar o mesmo driver
 * @author mizael.bragatti
 *
 */
public class AbstractPage {
	
	protected WebDriver driver;
	
	/**
	 * @Descricao M�todo construtor da Classe Abstrata para compartilhar o mesmo driver
	 * @param driver
	 */
	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void fecharBrowser() {
		driver.quit();
	}
	
	
	
	

}

