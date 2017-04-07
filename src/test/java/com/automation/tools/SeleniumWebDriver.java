package com.automation.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static com.automation.tools.Tools.isLinux;

/**
 * Created by tsvetan.karchev on 07/11/2016.
 */
public class SeleniumWebDriver {

    static private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>() {

        /*
         * initialValue() is called
         */
        @Override
        public synchronized WebDriver initialValue() {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("network.http.phishy-userpass-length", 255);
            if (isLinux()) System.setProperty("webdriver.gecko.driver", "/home/ceco/geckodriver");
            else System.setProperty("webdriver.gecko.driver", "D:\\geckodriver\\geckodriver.exe");
            return new FirefoxDriver(profile);
        }
    };

    static public synchronized WebDriver getWebDriver() {
        return webDriver.get();
    }

    static public synchronized WebDriver setWebDriver() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.http.phishy-userpass-length", 255);
        if (isLinux()) System.setProperty("webdriver.gecko.driver", "/home/ceco/geckodriver");
        else System.setProperty("webdriver.gecko.driver", "D:\\geckodriver\\geckodriver.exe");
        DesiredCapabilities capability = DesiredCapabilities.firefox();
        WebDriver webDriver2 = new FirefoxDriver(profile);
        webDriver.set(webDriver2);
        return webDriver2;
    }

    static public synchronized void close() {
        webDriver.get().quit();
    }



}


