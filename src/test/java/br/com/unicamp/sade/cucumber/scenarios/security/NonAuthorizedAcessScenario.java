package br.com.unicamp.sade.cucumber.scenarios.security;

/**
 * 
 * Non authorized access scenario.
 *
 */
public class NonAuthorizedAcessScenario {

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
//	@Given("^the page is open 6 \"([^\"]*)\"$")
//	public void home_is_open(String page) throws Throwable {
//		driver.get(page);
//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
//
//	@When("^i sign in and and navigate to \"([^\"]*)\"$")
//	public void login_and_create_admin(String page) throws Throwable {
//		waitForLoad(driver);
//		driver.findElement(By.xpath("//*[@id='account-menu']/span/span[2]")).click();
//		driver.findElement(By.xpath("//*[@id='login']/span[2]")).click();
//		driver.findElement(By.id("username")).sendKeys("user");
//		driver.findElement(By.id("password")).sendKeys("user");
//		driver.findElement(By.id("password")).submit();
//		driver.navigate().to(page);
//		waitForLoad(driver);
//	}
//
//	@Then("^an access denied error should be shown")
//	public void new_admin_created() throws Throwable {
//		waitForLoad(driver);
//		assertThat(driver.getCurrentUrl().equals("http://localhost:8080/#/user-management"));
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

