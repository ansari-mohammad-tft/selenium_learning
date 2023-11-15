package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class assigment3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/ehsan/Downloads/chromedriver-linux64/chromedriver");
        WebDriver driver = new ChromeDriver();
        String WEBURL = "https://www.shutterstock.com/";
        driver.get(WEBURL);
        driver.manage().window().maximize() ;
//        search icon
        driver.findElement(By.xpath("//div[3]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[2]/div[3]/div/div/div")).click();

        uploadFile(driver,"/home/ehsan/Downloads/download.jpeg");
        driver.findElement(By.className("mui-t7xql4-a-inherit-link")).click();
        scrollDown(driver);
        String photoID = getPhotoId(driver);

        scrollUp(driver);
        driver.findElement(By.className("mui-o90xvh-a-inherit-logo")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[1]/div[3]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[2]/div[2]/div[2]/div/div/input")).sendKeys(photoID);
        driver.findElement(By.xpath("//div[1]/div[3]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[2]/div[3]/button")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div[2]/div[1]/div/div/div[1]/div[1]/div/div[2]/div/button")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[11]/div[3]/div[2]/div[2]/button")).click();
        Login("","",driver);

    }



    private static String getPhotoId(WebDriver driver) {
        String imageID = driver.findElement(By.xpath("//div[3]/div/div/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/div/div/div[1]/div[1]/p")).getText();
        String id = imageID.split("ID:")[1];
//        System.out.println(id);
        return id;
    }

    public static void uploadFile(WebDriver driver,String filePath) throws InterruptedException {
        Thread.sleep(5000);
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys(filePath);
//        adding this because we need to wait to upload file
        Thread.sleep(9000);
    }
    public static void scrollDown(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");
    }
    public static void scrollUp(WebDriver driver){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(350,0)", "");
    }
    public static void Login(String username, String password,WebDriver driver){
        driver.findElement(By.xpath("//div[2]/div/form/div[3]/span/p/a")).click();
        driver.findElement(By.xpath("//div[2]/div/form/div[2]/div[1]/span[1]/div/div/input")).sendKeys(username);
        driver.findElement(By.xpath("//div[2]/div/form/div[2]/div[1]/span[2]/div/div/input")).sendKeys(password);
        driver.findElement(By.xpath("//div[2]/div/form/div[2]/div[2]/div[1]/span/button")).click();
    }

}
