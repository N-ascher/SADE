package br.com.unicamp.sade.cucumber.scenarios.account;

/**
 * 
 * Edit user profile scenario.
 *
 */
public class EditAccountProfileScenario {

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
//	@Given("^the page is open 1 \"([^\"]*)\"$")
//	public void the_edit_profile_page_is_open(String page) throws Throwable {
//		driver.get(page);
//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
//
//	@When("^i sign in and edit my information")
//	public void fill_information() throws Throwable {
//		driver.findElement(By.xpath("//*[@id='account-menu']/span/span[2]")).click();
//		driver.findElement(By.xpath("//*[@id='login']/span[2]")).click();
//		driver.findElement(By.id("username")).sendKeys("cesar");
//		driver.findElement(By.id("password")).sendKeys("cesar");
//		driver.findElement(By.id("password")).submit();
//		driver.navigate().to("http://localhost:8080/#/settings");
//		waitForLoad(driver);
//		driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("novosobrenome");
//		driver.findElement(By.xpath("//*[@id='lastName']")).submit();
//
//	}
//
//	@Then("^the new information shows up at my profile")
//	public void registration_complete() throws Throwable {
//		waitForLoad(driver);
//		assertThat(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div/strong")).isDisplayed());
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

