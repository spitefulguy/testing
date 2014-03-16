package go;

import goMailPages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alex on 3/16/14.
 */
public class AdvancedSearch {
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
    public void onSiteNonRF() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().advancedSearchOnSite("танк", "wikipedia.org").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains("танк — википедия"));
    }

    @Test
    public void onSiteRF() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().advancedSearchOnSite("танк", "ru.wikipedia.org").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains("танк — википедия"));
    }

    @Test
    public void searchDoc() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().advancedSearchByFormat("документ", "doc").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains(".doc"));
    }

    @Test
    public void searchXls() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().advancedSearchByFormat("документ", "xls").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains(".xls"));
    }

    @Test
    public void searchPdf() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().advancedSearchByFormat("документ", "pdf").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains(".pdf"));
    }

    @Test
    public void searchPpt() {
        MainPage mainPage = new MainPage(driver);
        String output = mainPage.getSearchFormElement().advancedSearchByFormat("документ", "ppt").getOutput();
        output = output.toLowerCase();
        Assert.assertTrue(output.contains(".ppt"));
    }

    @AfterMethod
    public void close() {
        this.driver.close();
    }
}
