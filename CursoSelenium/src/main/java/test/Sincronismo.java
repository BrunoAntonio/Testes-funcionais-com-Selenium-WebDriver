package test;

import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DSL;



public class Sincronismo {
	
	//https://stackoverflow.com/questions/15164742/combining-implicit-wait-and-explicit-wait-together-results-in-unexpected-wait-ti#answer-15174978
	

	private DSL dsl;
	
	

	@Before
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Bruno\\Desktop\\Java\\Eclipse-Workspace\\chromedriver\\chromedriver.exe");
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src//main//resources//componentes.html");
		dsl = new DSL();
		
	}

	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);  //espera obrigatoriamente 5 segundos
		dsl.escrever("novoCampo", "Deu certo?");
	}

	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); //espera o tempo necess�rio at� 30 segundos,
		dsl.clicarBotao("buttonDelay");
		dsl.escrever("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test //mais contole das esperas, testes mais rapidos
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(getDriver(), 30); //N�o e definido um valor para toda a aplica��o
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("novoCampo"))); //apenas para o elementos com problemas de sincronismo
		dsl.escrever("novoCampo", "Deu certo?");
	}
	
	
	
	
	
}