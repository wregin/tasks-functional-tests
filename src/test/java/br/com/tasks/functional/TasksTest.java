package br.com.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void naoDeveSalvarTarefaSemDescricao() {
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
	public void deveSalvarTarefaSemData() {
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
	public void deveSalvarTarefaComDataPassada() {
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