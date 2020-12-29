package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CryptoSteps {

   public static WebDriver driver;

    @Given("^Launch the coinmarketcap website$")
    public void i_launch_the_coinmarketcap_website () throws Throwable {
        launchbrowser();
        driver.get ("https://coinmarketcap.com/");
    }

    @When("^Click on show rows$")
    public void i_click_on_show_rows () throws InterruptedException {
        driver.findElement (By.xpath ("//div[@class='sc-16r8icm-0 tu1guj-0 hueEpF']")).click ();

        Thread.sleep (2000);
    }

    @And("^Select fifty from the dropdown list$")
    public void select_fifty_from_the_dropdown_list () throws InterruptedException {
        driver.findElement (By.xpath ("//button[@class='sc-1ejyco6-0 igBkAX'][2]")).click ();
        Thread.sleep (2000);

    }

    @Then("^Verify that fifty results should be displayed$")
    public void verify_that_fifty_results_should_be_displayed () throws InterruptedException {
        //Find last row element and verify against the expected value
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("50 results didn't load", bodyText.contains("Showing 1 - 50"));


    }


    @Given ("^Click on Add to watchlist icon for five random crypto currencies$")
    public void Click_on_Add_to_watchlist_icon_for_five_random_crypto_currencies() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions (driver);

         driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
         driver.findElement (By.xpath ("//div[@class='tableWrapper___3utdq']/table/tbody/tr[2]/td[1]/span[@class='sc-7f3up6-0 ewqrfa']/span[@class='icon-Star']")).click ();
        driver.findElement (By.xpath ("//div[@class='tableWrapper___3utdq']/table/tbody/tr[5]/td[1]/span[@class='sc-7f3up6-0 ewqrfa']/span[@class='icon-Star']")).click ();


        js.executeScript("window.scrollBy(0,500)");
        WebDriverWait wait = new WebDriverWait (driver, 20);

        WebElement BitCoinCash = driver.findElement (By.xpath ("//div[@class='tableWrapper___3utdq']/table/tbody/tr[6]/td[1]/span[@class='sc-7f3up6-0 ewqrfa']/span[@class='icon-Star']"));
        wait.until(ExpectedConditions.visibilityOf(BitCoinCash));
        actions.moveToElement(BitCoinCash).click().perform();


        WebElement BinanceCoin = driver.findElement (By.xpath ("//div[@class='tableWrapper___3utdq']/table/tbody/tr[9]/td[1]/span[@class='sc-7f3up6-0 ewqrfa']/span[@class='icon-Star']"));
        wait.until(ExpectedConditions.visibilityOf(BinanceCoin));
        actions.moveToElement(BinanceCoin).click().perform();
        js.executeScript("window.scrollBy(0,500)");

        WebElement Stellar = driver.findElement (By.xpath ("//div[@class='tableWrapper___3utdq']/table/tbody/tr[13]/td[1]/span[@class='sc-7f3up6-0 ewqrfa']/span[@class='icon-Star']"));

        wait.until(ExpectedConditions.visibilityOf(Stellar));
        actions.moveToElement(Stellar).click().perform();
        Thread.sleep (2000);
    }

   // And Open the Watchlist in different tab
    @And ("Open the Watchlist in different tab")
    public void And_Open_the_Watchlist_in_different_tab() throws InterruptedException {
        Actions actions = new Actions (driver);
        actions.contextClick ( driver.findElement(By.linkText ("Watchlist"))).build ().perform ();
        Thread.sleep (2000);
        actions.keyDown (Keys.COMMAND).sendKeys (Keys.HOME).click ().build ().perform ();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

    }

    //Then Verify the all 5 currencies selected are added into watchlist
    @Then("Verify the all five currencies selected are added into watchlist")
    public void verify_the_all_currencies_selected_are_added_into_watchlist() throws InterruptedException {
        Thread.sleep (2000);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText.contains("Ethereum"));
        Assert.assertTrue("Text not found!", bodyText.contains("Bitcoin Cash"));
        Assert.assertTrue("Text not found!", bodyText.contains("Stellar"));
        Assert.assertTrue("Text not found!", bodyText.contains("Binance Coin"));
        Assert.assertTrue("Text not found!", bodyText.contains("Litecoin"));
      //  driver.findElement(By.linkText ("Ethereum"))

    }

    public void launchbrowser() {
        String projectPath = System.getProperty ("user.dir");
        System.setProperty ("webdriver.chrome.driver", projectPath + "/src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver ();
        driver.manage ().window ().maximize ();
    }

    public static void closeBrowser () {
        driver.close ();
        driver.quit();
    }

    @After(order=1)
    public void afterScenario(Scenario scenario) {
        System.out.println("Log out the user and close the browser");
        if (scenario.isFailed()) {
            try {
                byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach (screenShot, "image/png","test");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        closeBrowser();
    }


}
