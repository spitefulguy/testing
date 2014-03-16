package go;

import goMailPages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import org.testng.annotations.*;
import org.testng.Assert;

import java.net.URL;
import goMailPages.ResultPage;

/**
 * Created by alex on 3/16/14.
 */


public class BasicSearch {
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
    public void outputFind() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().basicSearch("одноклассники").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains("одноклассники"));
    }

    @Test
    public void outputNotFind(){
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().basicSearch("вапрвавапваdfgdfDdfgdfdfghshfggfh").getOutput();
        Assert.assertTrue(output.contains(ResultPage.getNotFoundText()));
    }

    @Test
    public void outputTransliteration(){
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().basicSearch("jlyjrkfccybrb").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains("одноклассники"));
    }

    @AfterMethod
    public void close() {
        this.driver.close();
    }
}
