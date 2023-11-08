package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) {
//        WebElement clockout = driver.findElement(By.xpath(CLOCK_OUT_BUTTON));
        System.setProperty("webdriver.chrome.driver", "/home/ehsan/Downloads/chromedriver-linux64/chromedriver");
        WebDriver driver = new ChromeDriver();
        String WEBURL = "https://irctc.co.in/";
        driver.get(WEBURL);
        driver.manage().window().maximize() ;
        WebElement busIcon = driver.findElement(By.xpath("//div[10]/div/ul/li[5]/a/span"));
        busIcon.click();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement departure = driver.findElement(By.xpath("//div[3]/div[2]/div[2]/form/div[1]/input"));
        WebElement going = driver.findElement(By.xpath("//div[3]/div[2]/div[2]/form/div[2]/input"));
        departure.sendKeys("Delhi");
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("//body/ul[1]/li[1]/div")).click();
        going.sendKeys("Manali");
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("//body/ul[2]/li[1]/div")).click();
        WebElement date = driver.findElement(By.xpath("//div[3]/div[2]/div[2]/form/div[3]/input"));
        date.click();
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
        set_date(driver,15);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("//div[3]/div[2]/div[2]/form/div[4]/button")).click();
//        select seat
        driver.findElement(By.xpath("//div[3]/div[3]/div[1]/div[7]/button")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("//div[3]/div[3]/div[5]/div[2]/table/tbody/tr[1]/td[1]/input")).click();
        driver.findElement(By.xpath("//div[3]/div[3]/div[5]/div[2]/table/tbody/tr[1]/td[1]/input")).click();
        driver.findElement(By.xpath("//div[3]/div[3]/div[5]/div[2]/table/tbody/tr[1]/td[1]/input")).click();


        driver.findElement(By.xpath("//div[3]/div[3]/div[5]/div[1]/div[2]/div[4]/span[6]")).click();
        driver.findElement(By.xpath("//div[3]/div[3]/div[5]/div[1]/div[2]/div[5]/span[6]")).click();

        driver.findElement(By.xpath("//div[3]/div[3]/div[5]/div[2]/div[2]/div[4]/button")).click();
//        login
        driver.findElement(By.xpath("//app-login/div[1]/div/form/div[1]/input")).sendKeys("username");
        driver.findElement(By.xpath("//app-login/div/div[1]/form/div[2]/input")).sendKeys("password");

        driver.findElement(By.xpath("//app-login/div[1]/form/div[3]/button")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

//        select state
        Select drpCountry = new Select(driver.findElement(By.name("state")));
        drpCountry.selectByVisibleText("UTTAR PRADESH");
//    filling the Passenger  details
        driver.findElement(By.xpath("//div/form/div/div/div[1]/div[4]/div[3]/div[2]/input")).sendKeys("Passenger x");
        driver.findElement(By.xpath("//div/form/div/div/div[1]/div[4]/div[4]/div[2]/input")).sendKeys("Passenger x");
//        select gender
        List<WebElement> gender = driver.findElements(By.name("select"));
        Select gender1= new Select(gender.get(0));
        gender1.selectByValue("M");
        Select gender2= new Select(gender.get(1));
        gender2.selectByValue("M");
//        fill age
        driver.findElement(By.xpath("//div/form/div[1]/div[4]/div[3]/div[4]/input")).sendKeys("24");
        driver.findElement(By.xpath("//div/form/div[1]/div[4]/div[4]/div[4]/input")).sendKeys("24");

//        I agree check box
        driver.findElement(By.xpath("//div/form/div[3]/div[2]/div/input")).click();

        driver.findElement(By.xpath("//div/form/div[3]/div[2]/button")).click();
    }

    public static void set_date(WebDriver driver,int days){
        LocalDate currentDate = LocalDate.now(); // November 10, 2023

        // Calculate the target date (15 days from the current date)
        LocalDate targetDate = currentDate.plusDays(days);

        // Format the target date in the expected date format (if needed)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d"); // Adjust the format as per the date picker on the website
        String targetDateFormatted = targetDate.format(formatter);

        // Find the <a> element with the target date and click it
        WebElement datePickerTable = driver.findElement(By.className("ui-datepicker-calendar"));
        List<WebElement> dayElements = datePickerTable.findElements(By.tagName("a"));

        for (WebElement dayElement : dayElements) {
            if (dayElement.getText().equals(targetDateFormatted)) {
                dayElement.click();
                break;
            }
        }

    }

}
