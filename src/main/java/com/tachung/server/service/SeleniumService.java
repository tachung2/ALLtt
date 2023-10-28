package com.tachung.server.service;


import lombok.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class SeleniumService {
    public String getSubscriptionDate(String email, String password) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedrivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.netflix.com/kr/login");

            driver.findElement(By.id("id_userLoginId")).sendKeys(email);
            driver.findElement(By.id("id_password")).sendKeys(password);
            driver.findElement(By.className("login-button")).click();

            // 페이지가 완전히 로드될 때까지 최대 10초간 대기합니다.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            driver.get("https://www.netflix.com/kr/Browse");
            // 로그인 후 메인 페이지의 특정 요소가 로드될 때까지 대기합니다. (예: 메인 페이지의 로고)
            // 이 부분은 실제 메인 페이지의 요소에 따라 조정이 필요합니다.

            driver.get("https://www.netflix.com/kr/YourAccount");

            // account-section-membersince 클래스를 갖는 요소가 나타날 때까지 대기합니다.
            WebElement subscriptionDateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".account-section-membersince")));
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(20));
            String subscriptionDateText = subscriptionDateElement.getText();

            return subscriptionDateText;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching data";
        } finally {
            driver.quit();
        }
    }
}
