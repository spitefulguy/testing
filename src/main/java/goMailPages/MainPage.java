package goMailPages;

import org.openqa.selenium.WebDriver;

/**
 * Created by alex on 3/15/14.
 */
public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchPageElement getSearchFormElement() {
        return new SearchPageElement(driver);
    }
}
