package test;

import static core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.BaseTest;
import page.CampoTreinoPage;

public class Cadastro extends BaseTest{


	private CampoTreinoPage page;
	
	@Before 
	public void inicializa() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Bruno\\Desktop\\Java\\Eclipse-Workspace\\chromedriver\\chromedriver.exe");
		getDriver().get("file:///"+System.getProperty("user.dir")+"/src//main//resources//componentes.html");
		page= new CampoTreinoPage();
	}
	
	
	@Test
	public void deveRealizarCadastroComSucesso(){
		page.setNome("Bruno");
		page.setSobrenome("Cardoso");
		page.setSexoMasculino();
		page.setComidaPizza();
		page.setEscolariade("Mestrado");
		page.setEsporte("Natacao");
		page.cadastrar();

		Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertTrue(page.obterNomeCadastro().endsWith("Bruno"));
		Assert.assertEquals("Sobrenome: Cardoso", page.obterSobrenomeCadastro());
		Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Comida: Pizza", page.obterComidaCadastro());
		Assert.assertEquals("Escolaridade: mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Esportes: Natacao", page.obterEsporteCadastro());
	}
	
}
