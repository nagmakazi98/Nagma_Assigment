package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DashBoard {

    String url ="https://proschool.ai/dashboard/home";

    WebDriver driver = new ChromeDriver();

    public void viewBoard(){
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
        }
    }
    
}
