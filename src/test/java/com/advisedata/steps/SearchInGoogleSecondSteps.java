package com.advisedata.steps;

import com.advisedata.tools.ThreadLocalCookieStore;
import com.advisedata.tools.Tools;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchInGoogleSecondSteps extends Tools {

    @Then("^verify that there is still hot chicks in Google!$")
    public void verify_that_there_is_still_hot_chicks_in_Google() throws Throwable {
        Thread.sleep(10000);
        destroyDriver();
    }

}
