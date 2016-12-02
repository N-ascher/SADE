package br.com.unicamp.sade.cucumber.scenarios.security;

/**
 * 
 * Malicious username (SQL Injection for example) scenario.
 *
 */
public class MaliciousUsernameScenario {

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
//	@Given("^the page is open 5 \"([^\"]*)\"$")
//	public void home_is_open(String page) throws Throwable {
//		driver.get(page);
//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
//
//	@When("^sign up with a malicious username \"([^\"]*)\"$")
//	public void login_and_create_admin(String username) throws Throwable {
//		waitForLoad(driver);
//		//variaveis auxiliares
//		driver.findElement(By.xpath("//*[@id='user']/a")).click();
//		WebElement element9 = driver.findElement(By.name("login"));
//		element9.sendKeys(") DROP TABLE *");
//	}
//
//	@Then("^the username should not be accepted")
//	public void new_admin_created() throws Throwable {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
//		WebElement element = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("/html/body/div[3]/div/div/form/div[2]/div[1]/div[1]/div/p[4]"))));
//		assertThat(element.isDisplayed());
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

