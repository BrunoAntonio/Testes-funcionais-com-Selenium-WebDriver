package core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import core.Propriedades;
import core.Propriedades.tipoExecucao;

public class DriverFactory {

//	private static WebDriver driver;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {

		WebDriver driver = null;
		if (Propriedades.TIPO_EXECUCAO == tipoExecucao.LOCAL) {
			switch (Propriedades.BROWSER) {
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case CHROME:
				driver = new ChromeDriver();
				break;
			case IE:
				driver = new InternetExplorerDriver();
				break;

			}
		}
		if (Propriedades.TIPO_EXECUCAO == tipoExecucao.GRID) {
			DesiredCapabilities cap = null;
			switch (Propriedades.BROWSER) {
			case FIREFOX:
				cap = DesiredCapabilities.firefox();
				break;
			case CHROME:
				cap = DesiredCapabilities.chrome();
				break;
			case IE:
				cap = DesiredCapabilities.internetExplorer();
				break;

			}
			try {
				// URL obtido do java -jar selenium-server-standalone-3.4.0.jar -role hub ->
				// Nodes should register to....
				driver = new RemoteWebDriver(new URL("http://.../wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.err.println("Falha na conex�o com o GRID");
				e.printStackTrace();
			}
		}

		if (Propriedades.TIPO_EXECUCAO == tipoExecucao.NUVEM) {
			DesiredCapabilities cap = null;
			switch (Propriedades.BROWSER) {
			case FIREFOX:
				cap = DesiredCapabilities.firefox();
				break;
			case CHROME:
				cap = DesiredCapabilities.chrome();
				break;
			case IE:
				cap = DesiredCapabilities.internetExplorer();
				cap.setCapability("platform", "Windows 7");
				cap.setCapability("version", "11.0");
				break;
			}
			try {
				// URL obtido da saucelabs
				driver = new RemoteWebDriver(new URL("http://password:chave@ondemand.saucelabs.com:../wd/hub"), cap);
			} catch (MalformedURLException e) {
				System.err.println("Falha na conex�o com o GRID");
				e.printStackTrace();
			}
		}

		driver.manage().window().setSize(new Dimension(1200, 765));
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		if (threadDriver != null) {
			threadDriver.remove();
		}
	}

}