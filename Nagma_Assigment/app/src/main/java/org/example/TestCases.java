package org.example;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            driver.get("https://www.makemytrip.com/");

            // Close any pop-up if appears
            closePopupIfPresent(driver, wait);

            // Navigate to Flights page
            // WebElement flightsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-cy='menu_Flights']")));
            WebElement flightsTab = driver.findElement(By.xpath("//li[@data-cy='menu_Flights']"));
            flightsTab.click();

            // Select "From" as "Bangalore"
            WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("fromCity")));
            fromInput.click();
            WebElement fromCity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='From']")));
            fromCity.sendKeys("Bangalore");
            // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(),'Bangalore, India')]"))).click();

              // // Select "To" as "Dubai" 
            WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("toCity")));
            toInput.click();

          //Scroll to the element to make sure it's in the viewport
            WebElement planningInternationalTrip = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'Planning an international holiday?')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", planningInternationalTrip);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", planningInternationalTrip);

            ////*[@class='value' and text()='ANYWHERE']
            WebElement ToCity = driver.findElement(By.xpath("//*[@class='value' and text()='ANYWHERE']"));
            ToCity.click();
            Thread.sleep(2000);


            WebElement EnterCity = driver.findElement(By.xpath("(//input[@placeholder='Enter City' and @autocomplete='false'])[2]"));
            EnterCity.sendKeys("Dubai");

            WebElement SelectDubai = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'Dubai, United Arab Emirates')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SelectDubai);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SelectDubai);
            // SelectDubai.click();
            Thread.sleep(2000);


            // Click on "DATES&DURATION"
            WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='DEPARTURE']")));
            dateInput.click();

            Thread.sleep(2000);

            //Select Month
            WebElement SelectMonth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'December, 2024')]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", SelectMonth);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SelectMonth);

            Thread.sleep(2000);

            // // Select "December 2024" 
            // selectMonth(driver, wait, "December 2024");
            WebElement applyButton = driver.findElement(By.xpath("//button[contains(text(),'Apply')]"));
            applyButton.click();

             // Initiate the search
             WebElement searchButton = driver.findElement(By.xpath("//button[contains(text(),'Search')]"));
             searchButton.click();

             Thread.sleep(5000);
   

             // Step 8: Retrieve all dates and flight prices for December 2024
             List<Integer> prices = retrieveFlightPrices(driver, wait);

             // Step 9: Calculate the median price for that month
             int medianPrice = calculateMedian(prices);
 
             // Step 10: Select flights lower than median price
             List<Integer> belowMedianPrices = filterPricesBelowMedian(prices, medianPrice);

             System.out.println("Select flights lower than median price");
 
             // Step 11: Narrow down to weekends or choose the lowest price
             int selectedDatePrice = selectBestDate(driver, wait, belowMedianPrices);

             System.out.println(" Narrow down to weekends or choose the lowest price");
 
             // Verify that at least one flight is available for the selected date
             verifyFlightAvailability(driver, wait, selectedDatePrice);

             System.out.println("at least one flight is available for the selected date");
 
            
        } finally {
            driver.quit();
        }
    }
    // private static void selectMonth(WebDriver driver, WebDriverWait wait, String monthYear) {
    //     // Wait until the month list is visible
    //     wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='monthList']")));
        
    //     // Get the list of month elements
    //     List<WebElement> monthElements = driver.findElements(By.xpath("//div[@class='monthList']//div[@class='monthItem']"));
        
    //     for (WebElement monthElement : monthElements) {
    //         // Check if the monthElement contains the target month and year
    //         if (monthElement.getText().contains(monthYear)) {
    //             monthElement.click();
    //             break;
    //         }
    //     }
        
    //     // // Wait until the "Apply" button is visible and clickable
    //     // WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Apply')]")));
    //     // applyButton.click();
    // }

    private static void closePopupIfPresent(WebDriver driver, WebDriverWait wait) {
        try {
            WebElement dismissPopup = wait.until(ExpectedConditions.elementToBeClickable(By.className("commonModal__close")));
            dismissPopup.click();
        } catch (Exception e) {
            // No popup to close
        }
    }
    private static List<Integer> retrieveFlightPrices(WebDriver driver, WebDriverWait wait) {
        // Wait for prices to be visible and scroll into view if necessary
        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='calPrice']")));
        List<Integer> prices = new ArrayList<>();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (WebElement priceElement : priceElements) {
            js.executeScript("arguments[0].scrollIntoView(true);", priceElement); // Scroll into view
            try {
                int price = Integer.parseInt(priceElement.getText().replaceAll("[^\\d]", ""));
                prices.add(price);
            } catch (NumberFormatException e) {
                System.err.println("Invalid price format: " + priceElement.getText());
            }
        }
        return prices;
    }

    
    private static int calculateMedian(List<Integer> prices) {
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Price list is empty, cannot calculate median.");
        }
        Collections.sort(prices);
        int size = prices.size();
        if (size % 2 == 0) {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2;
        } else {
            return prices.get(size / 2);
        }
    }
    private static List<Integer> filterPricesBelowMedian(List<Integer> prices, int medianPrice) {
        List<Integer> belowMedianPrices = new ArrayList<>();
        for (int price : prices) {
            if (price < medianPrice) {
                belowMedianPrices.add(price);
            }
        }
        return belowMedianPrices;
    }

    private static int selectBestDate(WebDriver driver, WebDriverWait wait, List<Integer> belowMedianPrices) {
        List<WebElement> dateElements = driver.findElements(By.xpath("//*[@class='appendBottom3 boldFont darkText font14']"));
        int selectedDatePrice = Integer.MAX_VALUE;
        for (WebElement dateElement : dateElements) {
            String dateText = dateElement.getText();
            int price = Integer.parseInt(dateElement.findElement(By.xpath(".//following-sibling::span[@class='calPrice']")).getText().replaceAll("[^\\d]", ""));
            if (belowMedianPrices.contains(price) && (isWeekend(dateText) || price < selectedDatePrice)) {
                selectedDatePrice = price;
            }
        }
        return selectedDatePrice;
    }
    private static boolean isWeekend(String dateText) {
        try {
            // Define the date format that matches the format of dateText
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            // Parse the dateText to a LocalDate object
            LocalDate date = LocalDate.parse(dateText, formatter);
            
            // Get the day of the week from the parsed date
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            
            // Check if the day of the week is Saturday or Sunday
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
            
        } catch (DateTimeParseException e) {
            // Handle the exception if the dateText cannot be parsed
            System.err.println("Invalid date format: " + dateText);
            return false;
        }
    }

    private static void verifyFlightAvailability(WebDriver driver, WebDriverWait wait, int selectedDatePrice) {
        // Wait for the flight results to be loaded on the page
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='roundTripFlightsSection']")));
        
        // Locate all flight result elements on the page
        List<WebElement> flightElements = driver.findElements(By.xpath("//div[@class='roundTripFlightsSection']"));
        
        // Flag to check if at least one flight is available for the selected date price
        boolean isFlightAvailable = false;
        
        // Iterate through each flight element to check availability
        for (WebElement flightElement : flightElements) {
            // Extract the price of the current flight
            String priceText = flightElement.findElement(By.xpath("//*[@class='blackFont flightPrice font14 boldFont']")).getText();
            int flightPrice = Integer.parseInt(priceText.replaceAll("[^\\d]", ""));
            
            // Check if the flight price matches the selected date price
            if (flightPrice == selectedDatePrice) {
                isFlightAvailable = true;
                break;
            }
        }
        
        // Print the result of the availability check
        if (isFlightAvailable) {
            System.out.println("There is at least one flight available for the selected date price: " + selectedDatePrice);
        } else {
            System.out.println("No flights available for the selected date price: " + selectedDatePrice);
        }
    }





    
}
