package br.com.unicamp.sade.cucumber.selenium.pages.interview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.stream.Collectors;

public class InterviewPage {
    private WebDriver driver;

    public InterviewPage(WebDriver driver) {
        this.driver = driver;
    }

    public int hourValue() {
        return Integer.valueOf(driver.findElement(By.id("hourValue")).getText());
    }

    public Set<String> answers() {
        return driver.findElements(By.className("question-answer"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toSet());
    }

    public Set<String> comments() {
        return driver.findElements(By.className("interview-comment"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toSet());
    }
}
