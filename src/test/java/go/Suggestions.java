package go;

import goMailPages.MainPage;
import goMailPages.ResultPage;
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
public class Suggestions {
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
    public void linkSuggested(){
        MainPage mainPage = new MainPage(driver);
        String suggestions = mainPage.getSearchFormElement().enterQuery("одноклассники").getSuggestions();
        Assert.assertTrue(suggestions.contains(ResultPage.getLinkSuggestedText()));
    }

    @Test
    public void wordSuggestedByItsBeginning(){
        MainPage mainPage = new MainPage(driver);
        String suggestions = mainPage.getSearchFormElement().enterQuery("пфаль").getSuggestions();
        suggestions = suggestions.toLowerCase();
        Assert.assertTrue(suggestions.contains("пфальцграф"));
    }

    @Test
    public void suggestedForTransliteration(){
        MainPage mainPage = new MainPage(driver);
        String suggestions = mainPage.getSearchFormElement().enterQuery("jlyjrkfccybrb").getSuggestions();
        suggestions = suggestions.toLowerCase();
        Assert.assertTrue(suggestions.contains("одноклассники"));
    }

    @AfterMethod
    public void close() {
        this.driver.close();
    }
}
