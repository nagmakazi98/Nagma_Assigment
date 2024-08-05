package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Login {

    String url ="https://proschool.ai/";

    WebDriver driver = new ChromeDriver();

    public void navigateTologinPage(){ //not tested
        if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
        }

         //locating login button
         WebElement loginBttn = driver.findElement(By.xpath("//a[@href='/login']"));
         loginBttn.click();
    }

    public void performLogin() throws InterruptedException{

        WebElement email_text_box = driver.findElement(By.xpath("//input[@name=\"email\"]"));
        email_text_box.sendKeys("nagmakazi8652@gmail.com");

        Thread.sleep(2000);

        WebElement password_text_box = driver.findElement(By.xpath("//input[@name=\"password\"]"));
        password_text_box.sendKeys("Test@1998");

        Thread.sleep(1000);

        WebElement submit = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        System.out.println("Login Successfull");
        submit.click();
    }
    
    public void performLoginAsTeacher(){

        WebElement TeacherBttn = driver.findElement(By.xpath("//button[text()='Teacher']"));
        TeacherBttn.click();
    }

    public void performLoginAsStudent(){

        WebElement StudentBttn = driver.findElement(By.xpath("//button[text()=\"Student\"]"));
        StudentBttn.click();

    }

    public boolean VerifyLoggedInUser(){ //not tested
        WebElement username_label = driver.findElement(By.className("dashboardHeaderProfileText"));
         return username_label.getText().equals("Nagma");
        ////p[@class="dashboardHeaderProfileText"]

        // if(username_label.getText().contains("Nagma")){
        //     System.out.println("Profile loaded");
        // }else{
        //     System.out.println("Profile not loaded");
        }
    
    }


