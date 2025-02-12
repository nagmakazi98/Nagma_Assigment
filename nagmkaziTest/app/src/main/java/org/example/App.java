/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {
    
    public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));
    }


    public static void main(String[] args) {
      
        ChromeOptions options = new ChromeOptions();
       WebDriver driver = new ChromeDriver(options);

		WebDriverManager.chromedriver().setup();
		//WebDriver driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        // Open the website
        driver.get("https://proschool.ai/");

               // Close the browser
        driver.quit();
    }
}
