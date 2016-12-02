package br.com.unicamp.sade.cucumber.scenarios.usermanagement;

/**
 * 
 * Scenario to create an admin user.
 *
 */
public class CreateConpecUserScenario {

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
//	@Given("^the page is open 7 \"([^\"]*)\"$")
//	public void home_is_open(String page) throws Throwable {
//		driver.get(page);
//		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	}
//
//	@When("^i navigate to the create new admin dialogue and i fill all the necessary information")
//	public void login_and_create_admin() throws Throwable {
//		waitForLoad(driver);
//		//variaveis auxiliares
//		String str = "adminnovo@admin.com";
//		//aperta entre
//		driver.findElement(By.xpath("//*[@id='account-menu']/span/span[2]")).click();
//		driver.findElement(By.xpath("//*[@id='login']")).click();
//		driver.findElement(By.id("username")).sendKeys("admin");
//		driver.findElement(By.id("password")).sendKeys("admin");
//		driver.findElement(By.id("password")).submit();
//		driver.navigate().to("http://localhost:8080/#/user-management");;
//		driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/button/span[2]")).click();
//		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[2]/input")).sendKeys("adminnovo");
//		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[3]/input")).sendKeys("admin");
//		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[4]/input")).sendKeys("novo");
//		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[5]/input")).sendKeys(str);
//		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[7]/select")).click();
//		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[7]/select/option[1]")).click();
//		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[2]/div[8]/select/option[2]")).click();
//		driver.findElement(By.xpath("/html/body/div[1]/div/div/form/div[3]/button[2]/span[2]")).click();
//
//	}
//
//	@Then("^a new admin should be registered")
//	public void new_admin_created() throws Throwable {
//		String str = "adminnovo@admin.com";
//		waitForLoad(driver);
//		assertThat(driver.getPageSource().contains(str));
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

