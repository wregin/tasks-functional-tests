package br.com.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();  // utilizado para conectar na máquina localmente, foi alterado para ser utilizado o selenium
		// no fundo no caso desse teste será a mesma coisa pois vai rodar em máquina local, mas a ideia é como que em outros lugares
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://172.19.0.1:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.15.26:9999/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a task(descricao)
			driver.findElement(By.id("task")).sendKeys("teste via selenium 2");
					
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2045");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar a mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", mensagem);
		} finally {
			// fechar o navegador
			driver.quit();
			
		}
		
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
					
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2045");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar a mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			// fechar o navegador
			driver.quit();
			
		}
		
	}
	
	@Test
	public void deveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a task(descricao)
			driver.findElement(By.id("task")).sendKeys("teste via selenium 2");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar a mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			// fechar o navegador
			driver.quit();
			
		}
		
	}
	
	@Test
	public void deveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			// clicar em add todo
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a task(descricao)
			driver.findElement(By.id("task")).sendKeys("teste via selenium 2");
					
			// escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			// clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			// validar a mensagem de sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			// fechar o navegador
			driver.quit();
			
		}
		
	}

}  