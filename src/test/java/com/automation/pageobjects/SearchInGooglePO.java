package com.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.automation.tools.Tools;

/**
 * Created by tsvetan.karchev on 08/11/2016.
 */
public class SearchInGooglePO extends Tools {

    static synchronized public WebElement getSearchField() {
        return findElementBy(By.id("lst-ib"));
    }

    static synchronized public WebElement getSearchBtn() {
        return findElementBy(By.id("_fZl"));
    }

}
