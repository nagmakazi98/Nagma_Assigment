package org.example;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        //driver= new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    public void endTest()
    {
        System.out.println("End Test: TestCases");
       // driver.close();
        driver.quit();

    }

    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");

        driver.get("https://www.opencart.com/index.php?route=cms/demo");
        
        // Click on the Registration button
        driver.findElement(By.xpath("//a[text()=\"Register\" and @class=\"btn btn-black navbar-btn\"]")).click();

        Thread.sleep(5000);

         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Click on the Registration button
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Register')]")));
        registerButton.click();

        // Wait for the registration form to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

        // driver.get("https://www.opencart.com/index.php?route=account/register");

        Thread.sleep(5000);

        // Fill out the registration form
        WebElement userName = driver.findElement(By.xpath("//input[@id=\"input-username\"]"));
        WebElement firstName = driver.findElement(By.xpath("//input[@id=\"input-firstname\"]"));
        WebElement lastName = driver.findElement(By.xpath("//input[@id=\"input-lastname\"]"));
        WebElement email = driver.findElement(By.xpath("//input[@id=\"input-email\"]"));
        Select country = new Select(driver.findElement(By.xpath("//select[@id=\"input-country\"]")));
        WebElement password = driver.findElement(By.xpath("//input[@id=\"input-password\"]"));

        userName.sendKeys("TestUser");
        firstName.sendKeys("Test");
        lastName.sendKeys("User");
        email.sendKeys("testuser@example.com");
        country.selectByVisibleText("United States");
        password.sendKeys("TestPassword123");


        // Submit the form by clicking on the Register button
        driver.findElement(By.xpath("//button[contains(text(),'Register')]")).click();

        // Validation check - Verify if the registration is successful
        String expectedMessage = "Welcome to OpenCart, your account has been created";
        String actualMessage = driver.findElement(By.xpath("//h1[contains(text(),'Welcome to OpenCart')]")).getText();

        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Registration successful: " + actualMessage);
        } else {
            System.out.println("Registration failed or an error occurred.");
        }
    }

    public void testCase02() throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        
        driver.get("https://www.opencart.com/index.php?route=cms/demo");

        // Click on the Login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Login')]")));
        loginButton.click();

        // Enter login credentials
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement passwordField = driver.findElement(By.name("password"));
        
        emailField.sendKeys("registered_email@example.com");
        passwordField.sendKeys("registered_password");

        // Verify 'Forgot Password' functionality
        WebElement forgotPasswordLink = driver.findElement(By.xpath("//a[contains(text(),'Forgotten Password')]"));
        forgotPasswordLink.click();

        // Complete the 'Forgot Password' journey (this might be specific to how OpenCart handles it)
        WebElement forgotEmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        forgotEmailField.sendKeys("registered_email@example.com");
        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        continueButton.click();

        // Assuming the next steps involve setting up a PIN
        WebElement pinField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("pin")));
        pinField.sendKeys("1234");
        WebElement pinContinueButton = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        pinContinueButton.click();

        // Check if the user navigates to the Welcome page or Home page
        try {
            WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Welcome')]")));
            System.out.println("Navigation successful: " + welcomeMessage.getText());
        } catch (Exception e) {
            System.out.println("Navigation failed or an error occurred.");
        }

    }
        public void testCase03()throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://www.opencart.com/index.php?route=cms/demo");

        // Login process
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Login')]")));
        loginButton.click();

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement passwordField = driver.findElement(By.name("password"));

        emailField.sendKeys("registered_email@example.com");
        passwordField.sendKeys("registered_password");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Login')]")));
        submitButton.click();

        // Navigate to Demo page
        WebElement demoLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Demo')]")));
        demoLink.click();

        // Select "VIEW STORE FRONT"
        WebElement viewStoreFrontButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'View Store Front')]")));
        viewStoreFrontButton.click();

        // Extract product details and write to an Excel file
        List<WebElement> products = driver.findElements(By.cssSelector(".product-layout .product-thumb"));

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Product Details");

        // Header Row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Product Name");
        header.createCell(1).setCellValue("Product Price");
        header.createCell(2).setCellValue("Discount Price");

        int rowNum = 1;

        for (WebElement product : products) {
            String productName = product.findElement(By.cssSelector(".caption h4 a")).getText();
            String productPrice = product.findElement(By.cssSelector(".price")).getText();
            String discountPrice = ""; // Modify the selector if there is a specific discount price element

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(productName);
            row.createCell(1).setCellValue(productPrice);
            row.createCell(2).setCellValue(discountPrice);
        }

        try (FileOutputStream fileOut = new FileOutputStream("ProductDetails.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Click on a product to see details
        if (!products.isEmpty()) {
            products.get(0).findElement(By.cssSelector(".caption h4 a")).click();
        }

        }

        public void testCase04()throws InterruptedException{

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            driver.get("https://www.opencart.com/index.php?route=cms/demo");
    
            // Login process
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Login')]")));
            loginButton.click();
    
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            WebElement passwordField = driver.findElement(By.name("password"));
    
            emailField.sendKeys("registered_email@example.com");
            passwordField.sendKeys("registered_password");
    
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Login')]")));
            submitButton.click();
    
            // Navigate to Demo page
            WebElement demoLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Demo')]")));
            demoLink.click();
    
            // Select "VIEW STORE FRONT"
            WebElement viewStoreFrontButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'View Store Front')]")));
            viewStoreFrontButton.click();
    
            // Extract product details and write to an Excel file
            List<WebElement> products = driver.findElements(By.cssSelector(".product-layout .product-thumb"));
    
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Product Details");
    
            // Header Row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Product Name");
            header.createCell(1).setCellValue("Product Price");
            header.createCell(2).setCellValue("Discount Price");
    
            int rowNum = 1;
    
            for (WebElement product : products) {
                String productName = product.findElement(By.cssSelector(".caption h4 a")).getText();
                String productPrice = product.findElement(By.cssSelector(".price")).getText();
                String discountPrice = ""; // Modify the selector if there is a specific discount price element
    
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(productName);
                row.createCell(1).setCellValue(productPrice);
                row.createCell(2).setCellValue(discountPrice);
            }
    
            try (FileOutputStream fileOut = new FileOutputStream("ProductDetails.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            // Click on a product to see details
            if (!products.isEmpty()) {
                products.get(0).findElement(By.cssSelector(".caption h4 a")).click();
            }
    
            // Check product details page displayed
            WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            System.out.println("Product details page displayed for: " + productTitle.getText());
    
            // Change the quantity of the product
            WebElement quantityField = driver.findElement(By.id("input-quantity"));
            quantityField.clear();
            quantityField.sendKeys("2");
    
            // Click on 'Add to Cart' button
            WebElement addToCartButton = driver.findElement(By.id("button-cart"));
            addToCartButton.click();
    
            // Check if the cart count is updated
            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cart-total")));
            String cartCount = cartButton.getText();
            System.out.println("Cart count updated to: " + cartCount);
        }

        public void testCase05()throws InterruptedException{

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            driver.get("https://www.opencart.com/index.php?route=cms/demo");
    
            // Login process
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Login')]")));
            loginButton.click();
    
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
            WebElement passwordField = driver.findElement(By.name("password"));
    
            emailField.sendKeys("registered_email@example.com");
            passwordField.sendKeys("registered_password");
    
            WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Login')]")));
            submitButton.click();
    
            // Navigate to Demo page
            WebElement demoLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Demo')]")));
            demoLink.click();
    
            // Select "VIEW STORE FRONT"
            WebElement viewStoreFrontButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'View Store Front')]")));
            viewStoreFrontButton.click();
    
            // Extract product details and write to an Excel file
            List<WebElement> products = driver.findElements(By.cssSelector(".product-layout .product-thumb"));
    
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Product Details");
    
            // Header Row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Product Name");
            header.createCell(1).setCellValue("Product Price");
            header.createCell(2).setCellValue("Discount Price");
    
            int rowNum = 1;
    
            for (WebElement product : products) {
                String productName = product.findElement(By.cssSelector(".caption h4 a")).getText();
                String productPrice = product.findElement(By.cssSelector(".price")).getText();
                String discountPrice = ""; // Modify the selector if there is a specific discount price element
    
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(productName);
                row.createCell(1).setCellValue(productPrice);
                row.createCell(2).setCellValue(discountPrice);
            }
    
            try (FileOutputStream fileOut = new FileOutputStream("ProductDetails.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
    
            // Click on a product to see details
            if (!products.isEmpty()) {
                products.get(0).findElement(By.cssSelector(".caption h4 a")).click();
            }
    
            // Check product details page displayed
            WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
            System.out.println("Product details page displayed for: " + productTitle.getText());
    
            // Change the quantity of the product
            WebElement quantityField = driver.findElement(By.id("input-quantity"));
            quantityField.clear();
            quantityField.sendKeys("2");
    
            // Click on 'Add to Cart' button
            WebElement addToCartButton = driver.findElement(By.id("button-cart"));
            addToCartButton.click();
    
            // Check if the cart count is updated
            WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cart-total")));
            String cartCount = cartButton.getText();
            System.out.println("Cart count updated to: " + cartCount);
    
            // Open cart page through cart icon
            cartButton.click();
            WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//strong[contains(text(),'View Cart')]")));
            viewCartButton.click();
    
            // Verify product in cart page
            WebElement cartProductName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".table-responsive .text-left a")));
            System.out.println("Product in cart: " + cartProductName.getText());
    
            // Change the quantity of the product in cart page
            WebElement cartQuantityField = driver.findElement(By.name("quantity[1]"));
            cartQuantityField.clear();
            cartQuantityField.sendKeys("3");
            driver.findElement(By.cssSelector("button[data-original-title='Update']")).click();
    
            // Click on Check Out
            WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Checkout")));
            checkoutButton.click();
    
            // Fill out checkout details (example details, modify as necessary)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-payment-firstname"))).sendKeys("John");
            driver.findElement(By.id("input-payment-lastname")).sendKeys("Doe");
            driver.findElement(By.id("input-payment-address-1")).sendKeys("123 Example St");
            driver.findElement(By.id("input-payment-city")).sendKeys("Example City");
            driver.findElement(By.id("input-payment-postcode")).sendKeys("12345");
            driver.findElement(By.id("input-payment-country")).sendKeys("United States");
            driver.findElement(By.id("input-payment-zone")).sendKeys("California");
    
            // Continue through checkout steps
            driver.findElement(By.id("button-payment-address")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-address"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("button-shipping-method"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.id("button-payment-method"))).click();
            driver.findElement(By.name("agree")).click(); // Agree to terms
            driver.findElement(By.id("button-confirm")).click(); // Confirm order
    
            // Verify order confirmation
            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success")));
            System.out.println("Order confirmation message: " + confirmationMessage.getText());

        }



    
}
