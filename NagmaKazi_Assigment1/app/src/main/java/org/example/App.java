/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {
   
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
 
         WebDriverManager.chromedriver().setup();
         //WebDriver driver = new ChromeDriver(options);
 
         driver.manage().window().maximize();

         TestCases tests = new TestCases(); // Initialize your test class

        //TODO: call your test case functions one after other here

        tests.testCase01();
        tests.testCase02();
        tests.testCase03();
        tests.testCase04();
        tests.testCase05();

        //END Tests


        tests.endTest(); // End your test by clearning connections and closing browser
    }
}
