package uk.co.baskinginthesun.util;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class BrowserDriver {
    private static Logger LOGGER = Logger.getLogger(BrowserDriver.class.getName());

    private static WebDriver webDriver;

    private synchronized static WebDriver getCurrentDriver() {
        if (webDriver == null) {
            try {
                final DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("takesScreenshot", true);

                webDriver = new PhantomJSDriver(capabilities);
            } finally{
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
        }
        return webDriver;
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            LOGGER.info("Closing the browser");
            close();
        }
    }

    public static void close() {
        try {
            getCurrentDriver().quit();
            webDriver = null;
            LOGGER.info("closing the browser");
        } catch (UnreachableBrowserException e) {
            LOGGER.info("cannot close browser: unreachable browser");
        }
    }

    public static void loadPage(final String url){
        getCurrentDriver().get(url);
    }

    public static void setWindowSize(final Dimension size) {
        getCurrentDriver().manage().window().setSize(size);
    }

    public static WebElement findLinkByText(final String linkText) {
        return getCurrentDriver().findElement(By.linkText(linkText));
    }

    public static WebElement findElementByPath(final String path) {
        return getCurrentDriver().findElement(By.xpath(path));
    }

    public static Actions user() {
        return new Actions(BrowserDriver.getCurrentDriver());
    }

    public static WebDriverWait pause() {
        return new WebDriverWait(BrowserDriver.getCurrentDriver(), 5);
    }

    public static String getUrl() {
        return getCurrentDriver().getCurrentUrl();
    }

    public static String getPageSource() {
        return getCurrentDriver().getPageSource();
    }
}
