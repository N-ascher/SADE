package br.com.unicamp.sade.cucumber.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.File;

public class Driver {

    private static WebDriver mDriver;

    public synchronized static WebDriver getCurrentDriver() {
        if (mDriver==null) {
            try {
                File classpathRoot = new File(System.getProperty("user.dir"));
                File chromedriver = new File(classpathRoot, "driver/chromedriver");
                System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());
                mDriver = new ChromeDriver();
            } finally{
                Runtime.getRuntime().addShutdownHook(
                        new Thread(new BrowserCleanup()));
            }
        }
        return mDriver;
    }

    private static class BrowserCleanup implements Runnable {
        public void run() {
            closeCurrentDriver();
        }
    }

    public static void closeCurrentDriver() {
        try {
            getCurrentDriver().quit();
            mDriver = null;
        } catch (UnreachableBrowserException e) {
        }
    }

}
