package suites;




import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import core.DriverFactory;
import pages.LoginPage;
import tests.ContaTest;
import tests.MovimentacaoTest;
import tests.RemoverMovimentacaoContaTest;
import tests.ResumoTest;
import tests.SaldoTest;



@RunWith(Suite.class)
@SuiteClasses({
	ContaTest.class,
	MovimentacaoTest.class,
	RemoverMovimentacaoContaTest.class,
	SaldoTest.class,
	ResumoTest.class	
})
public class SuiteGeral {
	
	public static LoginPage page = new LoginPage();
	
	/*
	
	@BeforeClass
	public static void inicializa(){
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Bruno\\Desktop\\Java\\Eclipse-Workspace\\chromedriver\\chromedriver.exe");
		page.acessarTelaInicial();
		page.setEmail("brunoantonio@hotmail.com");
		page.setSenha("1234");
		page.entrar();
	}
	
	
	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();
	}
	*/
	
	@BeforeClass
	public static void reset() {
		page.acessarTelaInicial();
		page.setEmail("brunoantonio@hotmail.com");
		page.setSenha("1234");
		page.entrar();
		page.resetar();
		
		DriverFactory.killDriver();
	}
	
}
