package goMailPages;

import org.apache.xpath.operations.Bool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by alex on 3/16/14.
 */

public class SearchPageElement {
    WebDriver driver;
    static final String SUGGESTIONS_ID = "go-suggests__items";
    //static final String RESULT_SNAPSHOT = "result__snp";
    static final String RESPONSE_WRAPPER_CLASS = "responses__wrapper";
    static final String GO_BUTTON_SELECTOR = ".go-form__submit";
    static final String ADVANCED_SEARCH_ID = "go-form__advanced-btn";
    static final String ADVANCED_SEARCH_SITE_ID = "ASW-site";
    static final String ADVANCED_SEARCH_SUBMIT_ID = "ASW-submit";
    static final String ADVANCED_SEARCH_WORD_CLASS = "ASW-files-word";
    static final String ADVANCED_SEARCH_PDF_CLASS = "ASW-files-pdf";
    static final String ADVANCED_SEARCH_EXCEL_CLASS = "ASW-files-excel";
    static final String ADVANCED_SEARCH_PPT_CLASS = "ASW-files-ppt";

    public SearchPageElement(WebDriver driver) {
        this.driver = driver;
    }

    public SearchPageElement enterQuery(String query) {
        driver.findElement(By.id("q")).sendKeys(query);
        return this;
    }

    public SearchPageElement basicSearch(String query) {
        driver.findElement(By.id("q")).sendKeys(query);
        clickGoButton();
        return this;
    }

    public SearchPageElement advancedSearchOnSite(String query, String onSite) {
        driver.findElement(By.id("q")).sendKeys(query);
        clickGoButton();
        waitPageToLoad(RESPONSE_WRAPPER_CLASS);
        driver.findElement(By.id(ADVANCED_SEARCH_ID)).click();
        driver.findElement(By.id(ADVANCED_SEARCH_SITE_ID)).sendKeys(onSite);
        driver.findElement(By.id(ADVANCED_SEARCH_SUBMIT_ID)).click();
        return this;
    }

    public SearchPageElement advancedSearchByFormat(String query, String format) {
        driver.findElement(By.id("q")).sendKeys(query);
        clickGoButton();
        waitPageToLoad(RESPONSE_WRAPPER_CLASS);
        driver.findElement(By.id(ADVANCED_SEARCH_ID)).click();
        if (format.equals("doc")) {
            driver.findElement(By.className(ADVANCED_SEARCH_WORD_CLASS)).findElement(By.tagName("em")).click();
        }else if (format.equals("xls")) {
            driver.findElement(By.className(ADVANCED_SEARCH_EXCEL_CLASS)).findElement(By.tagName("em")).click();
        }else if (format.equals("ppt")) {
            driver.findElement(By.className(ADVANCED_SEARCH_PPT_CLASS)).findElement(By.tagName("em")).click();
        }else if (format.equals("pdf")) {
            driver.findElement(By.className(ADVANCED_SEARCH_PDF_CLASS)).findElement(By.tagName("em")).click();
        }

        driver.findElement(By.id(ADVANCED_SEARCH_SUBMIT_ID)).click();
        return this;
    }

    public String getSuggestions() {
        waitPageToLoad(SUGGESTIONS_ID);
        return driver.findElement(By.className(SUGGESTIONS_ID)).getText();
    }

    public String getOutput() {
        waitPageToLoad(RESPONSE_WRAPPER_CLASS);
        return driver.findElement(By.className(RESPONSE_WRAPPER_CLASS)).getText();

    }
    private void clickGoButton() {
        driver.findElement(By.cssSelector(GO_BUTTON_SELECTOR)).click();
    }

    private void waitPageToLoad(final String elementClassAwait) {
        new WebDriverWait(this.driver, 25).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return d.findElement(By.className(elementClassAwait)).getText().length() > 0;
            }
        });
    }
}
