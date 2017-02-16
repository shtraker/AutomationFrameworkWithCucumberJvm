package com.advisedata.tools;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.jws.soap.SOAPBinding;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Tools {

    public static String envUrl = System.getProperty("envUrl");
    public static String seleniumPort = System.getProperty("seleniumPort");
    private static final String AlphabetCapital = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String Alphabet = "abcdefghijklmnopqrstuvwxyz";
    private static final Random RAND = new Random();
    private static String OS = null;

    static {
        if (System.getProperty("envUrl") == null)
            envUrl = "http://google.com";
        if (System.getProperty("seleniumPort") == null)
            seleniumPort = "4000";
    }

    static synchronized public void buildDriver() {
        SeleniumWebDriver.setWebDriver();
        SeleniumWebDriver.getWebDriver().get(envUrl);
        SeleniumWebDriver.getWebDriver().manage().window().maximize();
    }

    static synchronized public void destroyDriver() {
        SeleniumWebDriver.getWebDriver().quit();
    }

    static synchronized public WebDriver getWebDriver() {
        return SeleniumWebDriver.getWebDriver();
    }

    static synchronized public WebElement findElementBy(By by) {
        try {Thread.sleep(1000);} catch (Exception e) {}
        SeleniumWebDriver.getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(SeleniumWebDriver.getWebDriver(), 10);
        WebElement element = null;
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        SeleniumWebDriver.getWebDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return element;
    }

    static public void click(WebElement element) {
        doAction(element,"click","");
    }

    static public void clear(WebElement element) {
        doAction(element,"clear","");
    }


    static public void sendKeys(WebElement element,String keys) {
        doAction(element,"sendKeys",keys);
    }

    static public String getAttribute(WebElement element,String value){
        return doAction(element,"getAttribute",value);
    }

    static private String doAction(WebElement element, String action, String text){
        int br = 1;
        String result = null;
        while (br<=3) {
            try {
                switch (action){
                    case "click": element.click();break;
                    case "clear": element.clear();break;
                    case "sendKeys": element.sendKeys(text);break;
                    case "getAttribute": result = element.getAttribute(text);break;
                    default: Assert.assertTrue("There is no such Action",false);
                }
                br=30;
            }
            catch (Exception e) {
                System.out.println("EXCEPTION:"+e);
                br++;
                try {Thread.sleep(100);} catch (Exception ex) {}
                if (br==10) throw e;
            }
        }
        return result;
    }


    static public String generateRandomName(int len) {
        StringBuilder sb = new StringBuilder(len);
        sb.append(AlphabetCapital.charAt(RAND.nextInt(AlphabetCapital.length())));
        for (int i = 1; i < len; i++) {
            sb.append(Alphabet.charAt(RAND.nextInt(Alphabet.length())));
        }
        return sb.toString();
    }

    static public String generateRandomEmail(int userLen,int domainLen) {
        StringBuilder sb = new StringBuilder(userLen+domainLen+2);

        for (int i = 1; i <= userLen; i++) {
            sb.append(Alphabet.charAt(RAND.nextInt(Alphabet.length())));
        }
        sb.append("@");
        for (int i = 1; i <= domainLen; i++) {
            sb.append(Alphabet.charAt(RAND.nextInt(Alphabet.length())));
        }
        sb.append("-automation.com");

        return sb.toString();
    }

    static public String generateRandomDomain(int length) {
        StringBuilder sb = new StringBuilder(length+2);
        for (int i = 1; i <= length; i++) {
            sb.append(Alphabet.charAt(RAND.nextInt(Alphabet.length())));
        }
        sb.append(".com");
        return sb.toString();
    }

    static public String generateRandomTime(String format) throws ParseException {
        String[] values;
        final Random random = new Random();
        final int millisInDay = 24*60*60*1000;
        Time time = new Time((long)random.nextInt(millisInDay));
        values = time.toString().split(":");
        if (format == "HH:mm") return values[0]+":"+values[1];
        if (format == "HH:mm:ss") return time.toString();
        else return time.toString();
    }

    static public int generateRandomNumber(int min, int max) {
        Random random = new Random();
        int number = random.nextInt(max)+min;
        return number;
    }

    static private boolean isTextPresent(WebDriver driver, By by, String text)
    {
        try {
            return driver.findElement(by).getText().contains(text);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean verifyTextPresent(final String text) {
        boolean isPresent = false;
        int br=0;
        while (!isPresent&&br<=15) {
                try {
                br++;
                isPresent = SeleniumWebDriver.getWebDriver().getPageSource().contains(text);
                //isPresent = SeleniumWebDriver.getWebDriver().findElement(By.tagName("body")).getText().contains(text);
                if (isPresent) return true;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    public boolean verifyTextPresentNoCaseSensetive(String text) {
        boolean isPresent = false;
        String btext = null;
        text = text.toLowerCase();
        int br=0;
        while (!isPresent&&br<=50) {
            try {
                br++;
                btext = SeleniumWebDriver.getWebDriver().findElement(By.tagName("body")).getText().toLowerCase();
                isPresent = btext.contains(text);
                if (isPresent) return isPresent;
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    public boolean verifyWebElementPresent(By by) {

        WebDriverWait wait = new WebDriverWait(SeleniumWebDriver.getWebDriver(), 30);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

        if (element == null) return false;
        else return true;

    }

    public void assertTextPresent(String text) {
        Assert.assertTrue("The text '"+text+"' is not present on the page, please check!"
                ,verifyTextPresent(text));
    }

    public void assertWebElementText(WebElement element,String text) {
        Assert.assertTrue("The text '"+text+"' is not present on this Web Element, please check!",
                element.getText().contains(text));
    }


    public boolean waitUntilTextPresent(String text){
        boolean isPresent = verifyTextPresent(text);
        int br=1;
        while (isPresent&&br<=30) {
            try {
                br++;
                isPresent = getWebDriver().findElement(By.tagName("body")).getText().contains(text);
                if (!isPresent) return isPresent;
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    public BufferedImage convertImage(BufferedImage image, int x, int y){
        BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        bi.createGraphics().drawImage(image, 0, 0, x, y, null);
        return bi;
    }

    static public boolean compareImages(BufferedImage imgA, BufferedImage imgB) {
        float width,height,s,p=0;
        float percentage;
        // The images must be the same size.
        if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) {
            width = imgA.getWidth();
            height = imgA.getHeight();

            // Loop over every pixel.
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    // Compare the pixels for equality.
                    if (imgA.getRGB(x, y) == imgB.getRGB(x, y)) {
                        p++;
                    }
                }
            }
        } else {
            return false;
        }

        s = width * height;
        percentage = (s - p) / s;

        if (percentage < 0.2) return true;
            else {
                return false;
        }
    }


    static private String getOsName() {
        if(OS == null) { OS = System.getProperty("os.name"); }
        return OS;
    }

    static public boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    static public boolean isLinux() {
        return getOsName().startsWith("Linux");
    }

    static public void setWebElementOpacityTrue(String by) {
        if (getWebDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getWebDriver())
                    .executeScript("document.getElementById(\""+by+"\").style.opacity = \"1\";");
        }
    }

    static public void destroyHTMLEditor(String id) {
        if (getWebDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getWebDriver())
                    .executeScript("var editorContainer = $('#"+id+"');\n" +
                            "        editorContainer.find('.wysihtml5-sandbox').first().remove();\n" +
                            "        $(\"body\").removeClass(\"wysihtml5-supported\");\n" +
                            "        editorContainer.find(\"iframe.wysihtml5-sandbox, input[name='_wysihtml5_mode']\").first().remove();\n" +
                            "        editorContainer.find('.wysihtml5-toolbar').first().remove();\n" +
                            "        editorContainer.find(\".wysihtml5-new\").first().css(\"display\", \"block\");");
        }
    }

    static public void setWebElementVisibleTrue(String by) {
        if (getWebDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) getWebDriver())
                    .executeScript("document.getElementById('"+by+"').style.display='block';");
        }
    }

    static public String getSelectedOptionText(WebElement element) {
        return new Select(element).getFirstSelectedOption().getText();
    }

}
