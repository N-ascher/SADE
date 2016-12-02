package br.com.unicamp.sade.cucumber.scenarios.account;

/**
 * Forgot password scenario.
 */
public class ForgotPasswordScenario {

//    private WebDriver driver;
//
//    @Before
//    public void setUp() throws IOException {
//        File classpathRoot = new File(System.getProperty("user.dir"));
//        File chromedriver = new File(classpathRoot, "driver/chromedriver");
//        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
//        driver = new ChromeDriver();
//    }
//
//    @After
//    public void tearDown() {
//        driver.close();
//    }
//
//    @Given("^the page is open 2 \"([^\"]*)\"$")
//    public void the_page_is_open(String page) throws Throwable {
//        driver.get(page);
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//    }
//
//    @When("^i try to retrieve my password")
//    public void fill_information() throws Throwable {
//        //variaveis auxiliares
//        String str = "cesar@cesar.com";
//        driver.findElement(By.xpath("//*[@id='account-menu']")).click();
//        driver.findElement(By.xpath("//*[@id='login']/span[2]")).click();
//        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div/div[5]/div/a")).click();
//        driver.findElement(By.xpath("//*[@id='email']")).sendKeys(str);
//        driver.findElement(By.xpath("//*[@id='email']")).submit();
//
//    }
//
//    @Then("^an email with instructions should be sent to me")
//    public void registration_complete() throws Throwable {
//        waitForLoad(driver);
//        assertThat(driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div/div/div[3]/p")));
//    }
//
//    public void waitForLoad(WebDriver driver) {
//        ExpectedCondition<Boolean> pageLoadCondition = new
//                ExpectedCondition<Boolean>() {
//                    public Boolean apply(WebDriver driver) {
//                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
//                    }
//                };
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(pageLoadCondition);
//    }
}

