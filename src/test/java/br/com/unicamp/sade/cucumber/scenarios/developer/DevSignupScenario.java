package br.com.unicamp.sade.cucumber.scenarios.developer;

/**
 * 
 * Developer sign up scenario.
 *
 */
public class DevSignupScenario {

//	private WebDriver driver;
//
//	@Before
//	public void setUp() throws IOException{
//		File classpathRoot = new File(System.getProperty("user.dir"));
//		File chromedriver = new File(classpathRoot, "driver/chromedriver");
//		System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
//		driver = new ChromeDriver();
//	}
//
//	@After
//	public void tearDown(){
//		driver.close();
//	}
//
//	@Given("^the register page is open \"([^\"]*)\"$")
//	public void the_signUp_page_is_open(String page) throws Throwable {
//		driver.get(page);
//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
//
//	@When("^i fill all the necessary information")
//	public void fill_information() throws Throwable {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("name")));
//		//variaveis auxiliares
//		String str = "4email@email.com";
//		//dados pessoais
//		WebElement element = driver.findElement(By.name("name"));
//		element.sendKeys("teste");
//		WebElement element1 = driver.findElement(By.name("mobileNumber"));
//		element1.sendKeys("1231231231");
//		WebElement element2 = driver.findElement(By.name("document"));
//		element2.sendKeys("12312312311");
//		WebElement element3 = driver.findElement(By.name("street"));
//		element3.sendKeys("minharua");
//		WebElement element4 = driver.findElement(By.name("neighborhood"));
//		element4.sendKeys("meubairro");
//		WebElement element5 = driver.findElement(By.name("number"));
//		element5.sendKeys("11111");
//		WebElement element6 = driver.findElement(By.name("city"));
//		element6.sendKeys("cidade");
//		WebElement element7 = driver.findElement(By.name("state"));
//		element7.sendKeys("ES");
//		WebElement element8 = driver.findElement(By.name("postalCode"));
//		element8.sendKeys("123123");
//		//dados de usu�rio
//		WebElement button = driver.findElement(By.id("user"));
//		button.click();
//		WebElement element9 = driver.findElement(By.name("login"));
//		element9.sendKeys("4teste");
//		WebElement element10 = driver.findElement(By.name("email"));
//		element10.sendKeys(str);
//		WebElement element11 = driver.findElement(By.name("password"));
//		element11.sendKeys("teste");
//		WebElement element12 = driver.findElement(By.name("confirmPassword"));
//		element12.sendKeys("teste");
//		//experiencia
//		button = driver.findElement(By.id("experience"));
//		button.click();
//		WebElement element13 = driver.findElement(By.name("availability"));
//		element13.sendKeys("12");
//		WebElement element14 = driver.findElement(By.name("prospectedBy"));
//		element14.sendKeys("amigo");
//		element14.submit();
//	}
//
//	@Then("^my registration should be complete")
//	public void registration_complete() throws Throwable {
//		waitForLoad(driver);
//		assertThat(driver.getPageSource().contains("Cadastro salvo com sucesso!"));
//	}
//
//	public void waitForLoad(WebDriver driver) {
//		ExpectedCondition<Boolean> pageLoadCondition = new
//	    ExpectedCondition<Boolean>() {
//		public Boolean apply(WebDriver driver) {
//			return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
//			}
//		};
//	    WebDriverWait wait = new WebDriverWait(driver, 30);
//	    wait.until(pageLoadCondition);
//	 }
}

