package Tests;

import java.io.File;
import java.sql.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class publicacao {
	static WebDriver driver = new FirefoxDriver();
	String Test = "Publicação";
	@BeforeClass
	public static void abrirnavegador() {
		driver.get("https://twitter.com/login/");
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		String i = driver.getCurrentUrl();
		System.out.println(i);


		//Irá aguardar elemento estar clicável
		WebElement Login = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='js-username-field email-input js-initial-focus']")));
		//Insere no campo do Usuário
		driver.findElement(By.xpath("//*[@class='js-username-field email-input js-initial-focus']")).sendKeys("desafioautomaca");

		//Irá aguardar elemento estar clicável
		WebElement Senha = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='js-password-field']")));

		//Insere no campo da Senha
		driver.findElement(By.xpath("//*[@class='js-password-field']")).sendKeys("Teste123");

		//Irá aguardar elemento estar clicável
		WebElement Entrar = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='submit btn primary-btn']")));

		//Defini o WebElemento e faz o submit
		WebElement click = driver.findElement(By.xpath("//*[@class='submit btn primary-btn']"));
		click.submit();



	}
	//Após executar todos os testes ele irá fechar navegador
	@AfterClass
	public static void fecharnavegador() {
		System.out.print("\nFim dos Testes \n Navegador foi Fechado");
		driver.quit();
	}

	//Irá aplicar screenshot caso erro
	public void getscreenshot(Date date) throws Exception
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("C:/QuaTest/Twitter/Error/"+Test+"/"+"010101.jpeg"));	

	}

	//Testes de Publicação de Texto
	@Test
	public void PublicacaoTexto() throws  Exception  {

		System.out.println("Iniciando testes de Publicação de Texto...");

		try{
			String texto = "Texto a ser digitadoq";
			driver.findElement(By.xpath("//*[@class='tweet-box rich-editor notie']")).click();
			driver.findElement(By.xpath("//*[@class='tweet-box rich-editor notie is-showPlaceholder']")).sendKeys(texto);
			driver.findElement(By.xpath("//*[@class='btn primary-btn tweet-action tweet-btn js-tweet-btn']")).click();
			Thread.sleep(1000);

			String resultado = driver.findElement(By.xpath("//*[@class='TweetTextSize  js-tweet-text tweet-text']")).getText();

			Assert.assertEquals(texto, resultado);
			System.out.println(texto + " é igual " + resultado);

		}catch(Exception e)
		{
			Date date = new Date(System.currentTimeMillis());  

			getscreenshot(date);

		}
		System.out.println("Finalizado teste de Publicação de Texto");

	}
	
	//Testes de Publicação Enquete
	@Test
	public void PublicacaoEnquete() throws  Exception  {
		
		System.out.println("Iniciando testes de Publicação de Enquete...");

		try{
			String pergunta = "Sua perguntaq?";
			String resposta1 = "resposta 1q";
			String resposta2 = "resposta 2q";

			driver.findElement(By.xpath("//*[@class='tweet-box rich-editor notie']")).click();
			driver.findElement(By.xpath("//*[@class='btn icon-btn PollCreator-btn js-tooltip']")).click();
			driver.findElement(By.xpath("//*[@class='tweet-box rich-editor notie is-showPlaceholder']")).sendKeys(pergunta);
			
			driver.findElement(By.xpath("//*[@class='PollingCardComposer-option PollingCardComposer-option1']")).click();
			driver.findElement(By.xpath("//*[@class='PollingCardComposer-optionInput is-singleLine is-plainText u-borderUserColorLightFocus notie is-showPlaceholder']")).sendKeys(resposta1);

			driver.findElement(By.xpath("//*[@class='PollingCardComposer-option PollingCardComposer-option2']")).click();
			driver.findElement(By.xpath("//*[@class='PollingCardComposer-optionInput is-singleLine is-plainText u-borderUserColorLightFocus notie is-showPlaceholder']")).sendKeys(resposta2);

			driver.findElement(By.xpath("//*[@class='btn primary-btn tweet-action tweet-btn js-tweet-btn']")).click();
			Thread.sleep(2000);

			String PerguntaPost = driver.findElement(By.xpath("//*[@class='TweetTextSize  js-tweet-text tweet-text']")).getText();
			Assert.assertEquals(pergunta, PerguntaPost);
			System.out.println(pergunta + " é igual " + PerguntaPost);

		}
		catch(Exception e)
		{
			Date date = new Date(System.currentTimeMillis());  
			getscreenshot(date);

		}

		System.out.println("Finalizado teste de Publicação de Enquete");


	}





}