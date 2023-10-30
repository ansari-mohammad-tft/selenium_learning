package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

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
        WebElement busIcon = driver.findElement(By.xpath("/html/body/app-root/app-home/div[3]/div/app-main-page/div/div/div[10]/div/ul/li[5]/a/span"));
        busIcon.click();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        WebElement departure = driver.findElement(By.xpath("/html/body/app-root/ng-component/div[3]/div[2]/div[2]/form/div[1]/input"));
        WebElement going = driver.findElement(By.xpath("/html/body/app-root/ng-component/div[3]/div[2]/div[2]/form/div[2]/input"));
        departure.sendKeys("Delhi");
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("/html/body/ul[1]/li[1]/div")).click();
        going.sendKeys("Manali");
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("/html/body/ul[2]/li[1]/div")).click();
        WebElement date = driver.findElement(By.xpath("/html/body/app-root/ng-component/div[3]/div[2]/div[2]/form/div[3]/input"));
        date.click();
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS) ;
        WebElement next = driver.findElement(By.xpath("/html/body/div[3]/div/a[2]/span"));
        next.click();
//        click on 13 Nov
        driver.findElement(By.xpath("/html/body/div[3]/table/tbody/tr[3]/td[2]")).click();
//
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div[3]/div[2]/div[2]/form/div[4]/button")).click();
//        select seat
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/div/div/div[3]/div[3]/div[1]/div[7]/button")).click();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/div/div/div[3]/div[3]/div[5]/div/div/div[2]/div/div/div/div/table/tbody/tr[1]/td[1]/input")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/div/div/div[3]/div[3]/div[5]/div/div/div[2]/div/div/div/div/table/tbody/tr[1]/td[1]/input")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/div/div/div[3]/div[3]/div[5]/div/div/div[2]/div/div/div/div/table/tbody/tr[1]/td[1]/input")).click();


        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/div/div/div[3]/div[3]/div[5]/div/div/div[1]/div/div/div/div[2]/div[4]/span[6]")).click();
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/div/div/div[3]/div[3]/div[5]/div/div/div[1]/div/div/div/div[2]/div[5]/span[6]")).click();

        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/div/div/div[3]/div[3]/div[5]/div/div/div[2]/div[2]/div[4]/button")).click();
//        login
        driver.findElement(By.xpath("/html/body/app-root/ng-component/app-login/div/div/div/div/div/div[1]/div/form/div[1]/input")).sendKeys("username");
        driver.findElement(By.xpath("/html/body/app-root/ng-component/app-login/div/div/div/div/div/div[1]/div/form/div[2]/input")).sendKeys("password");

        driver.findElement(By.xpath("/html/body/app-root/ng-component/app-login/div/div/div/div/div/div[1]/div/form/div[3]/button")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;

//        select state
        Select drpCountry = new Select(driver.findElement(By.name("state")));
        drpCountry.selectByVisibleText("UTTAR PRADESH");

        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/form/div/div/div[1]/div[4]/div[3]/div[2]/input")).sendKeys("Traver x");
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/form/div/div/div[1]/div[4]/div[4]/div[2]/input")).sendKeys("Traver y");
//        select gender
        List<WebElement> gender = driver.findElements(By.name("select"));
        Select gender1= new Select(gender.get(0));
        gender1.selectByValue("M");
        Select gender2= new Select(gender.get(1));
        gender2.selectByValue("M");
//        fill age
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/form/div/div/div[1]/div[4]/div[3]/div[4]/input")).sendKeys("24");
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/form/div/div/div[1]/div[4]/div[4]/div[4]/input")).sendKeys("24");

//        I agree check box
        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/form/div/div/div[3]/div[2]/div/input")).click();

        driver.findElement(By.xpath("/html/body/app-root/ng-component/div/form/div/div/div[3]/div[2]/button")).click();
    }

    public static boolean clockOut(WebDriver driver) {
        try {
            driver.findElement(By.xpath("/html/body/xhr-app-root/div/xhr-home/div/home-dashboard/div/div/div/div/div[2]/div/div[1]/div[6]/home-attendance-clockin-widget/div/div[1]/div/div[2]/div/div[2]/div/div/button"));
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
