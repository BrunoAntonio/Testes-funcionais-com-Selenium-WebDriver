package suites;

import static core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


import test.Cadastro;

import test.RegrasCadastro;



@RunWith(Suite.class)
@SuiteClasses({
	Cadastro.class,
	RegrasCadastro.class,
	
})
public class SuiteTeste {
	
	@AfterClass
	public static void finalizaTudo() {
		killDriver();
	}

}
