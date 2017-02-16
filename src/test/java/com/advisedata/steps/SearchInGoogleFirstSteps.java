package com.advisedata.steps;

import com.advisedata.pageobjects.SearchInGooglePO;
import com.advisedata.tools.Tools;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchInGoogleFirstSteps extends Tools {

    String search = "hot chicks";

    @Given("^open the browser and navigate to google$")
    public void open_the_browser_and_navigate_to_google() throws Throwable {
        buildDriver();
    }

    @When("^submit in the search field hot chicks$")
    public void submit_in_the_search_field_hot_chicks() throws Throwable {
        sendKeys(SearchInGooglePO.getSearchField(),search);
        click(SearchInGooglePO.getSearchBtn());
    }

}
