package go;

import goMailPages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alex on 3/16/14.
 */
public class Ranking {
    WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "hub", "url"})
    void init(String browser, String hub, String url) throws MalformedURLException {
        if (browser.toLowerCase().equals("firefox")) {
            this.driver = new RemoteWebDriver(new URL(hub), DesiredCapabilities.firefox());
        }

        if (browser.toLowerCase().equals("chrome")) {
            this.driver = new RemoteWebDriver(new URL(hub), DesiredCapabilities.chrome());
        }
        driver.get(url);
    }

    @Test
    public void basicSearchRanking() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().basicSearch("человек").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains("человек разумный — википедия"));
    }

    @Test
    public void advancedSearchOnSiteRanking() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().advancedSearchOnSite("человек", "http://ru.wikipedia.org").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains("человек разумный — википедия"));
    }

    @AfterMethod
    public void close() {
        this.driver.close();
    }
}
