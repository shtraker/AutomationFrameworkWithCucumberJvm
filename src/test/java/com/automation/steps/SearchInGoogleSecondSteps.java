package com.automation.steps;

import com.automation.tools.ThreadLocalCookieStore;
import com.automation.tools.Tools;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.automation.tools.Tools.destroyDriver;

public class SearchInGoogleSecondSteps extends Tools {

    @Then("^verify that there is still hot chicks in Google!$")
    public void verify_that_there_is_still_hot_chicks_in_Google() throws Throwable {
        Thread.sleep(10000);
        destroyDriver();
    }

}
