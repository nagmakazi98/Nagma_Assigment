package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignUp {

    WebDriver driver;
    String url ="https://proschool.ai/";


    public SignUp(WebDriver driver){

     this.driver=driver;
        
    }

    public void navigaeToSignUp(){

    if(!driver.getCurrentUrl().equals(url)){
            driver.get(url);
    }
}

    public void PerformSignup(){
        //locating login button
        WebElement loginBttn = driver.findElement(By.xpath("//a[@href='/login']"));
        loginBttn.click();

        WebElement SignUpBttn = driver.findElement(By.xpath("//a[text()=\"Sign up\"]"));
        SignUpBttn.click();

        String currentUrl = driver.getCurrentUrl();

        if(currentUrl.contains("https://proschool.ai/signup")){

            System.out.println("Signup Page loaded");
        }else{
            System.out.println("Signup Page not loaded"); 
        }

        WebElement Email_text = driver.findElement(By.id(":r19:"));
        ////input[@id=":r19:"]
        Email_text.sendKeys("if11486@gmail.com");

        WebElement create_Password = driver.findElement(By.id(":r1a:"));
        create_Password.sendKeys("Test@1998");

        WebElement RegisterBttn = driver.findElement(By.xpath("//button[text()='Register']"));
        RegisterBttn.click();
   }
   public void performSignUpAsTeacher(){

    WebElement TeacherBttn = driver.findElement(By.xpath("//button[text()='Teacher']"));
    TeacherBttn.click();
}

public void performSignUpAsStudent(){

    WebElement StudentBttn = driver.findElement(By.xpath("//button[text()=\"Student\"]"));
    StudentBttn.click();

}

    }

