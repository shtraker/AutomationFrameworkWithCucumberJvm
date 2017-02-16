package com.automation.tools;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tsvetan.karchev on 07/11/2016.
 */
public class SeleniumWebDriver {

    static private ThreadLocal<RemoteWebDriver> webDriver = new ThreadLocal<RemoteWebDriver>() {

        /*
         * initialValue() is called
         */
        @Override
        public synchronized RemoteWebDriver initialValue() {
//            FirefoxProfile profile = new FirefoxProfile();
//            profile.setPreference("network.http.phishy-userpass-length", 255);
//            System.setProperty("webdriver.gecko.driver", "D:\\geckodriver\\geckodriver.exe");
//            return new FirefoxDriver(profile);
            URL url = null;
            try {
                url = new URL("http://automation.it-cover.com:"+Tools.seleniumPort+"/wd/hub");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            DesiredCapabilities capability = DesiredCapabilities.firefox();
            RemoteWebDriver webDriver = new RemoteWebDriver(url,capability);
            return webDriver;
        }
    };

    static public synchronized RemoteWebDriver getWebDriver() {
        return webDriver.get();
    }

    static public synchronized RemoteWebDriver setWebDriver() {
        URL url = null;
        try {
            url = new URL("http://automation.it-cover.com:"+Tools.seleniumPort+"/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        DesiredCapabilities capability = DesiredCapabilities.firefox();
        RemoteWebDriver webDriver2 = new RemoteWebDriver(url,capability);
        webDriver.set(webDriver2);
        return webDriver2;
    }

    static public synchronized void close() {
        webDriver.get().quit();
    }



}


