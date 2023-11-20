package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class assigment3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/home/ehsan/Downloads/chromedriver-linux64/chromedriver");
        WebDriver driver = new ChromeDriver();
        String WEBURL = "https://www.shutterstock.com/";
        driver.get(WEBURL);
        driver.manage().window().maximize() ;
//        search icon
        String searchByImageXpath="//div[@class=\"MuiBox-root mui-1i4x3e6-root\"]/button";
        driver.findElement(By.xpath(searchByImageXpath)).click();

        uploadFile(driver,"/home/ehsan/Downloads/download.jpeg");

        driver.findElement(By.className("mui-t7xql4-a-inherit-link")).click();
        scrollDown(driver);
        String photoID = getPhotoId(driver);
        addInCsv("/home/ehsan/Downloads/PhotoID.cvs",photoID);
        scrollUp(driver);
        driver.findElement(By.className("mui-o90xvh-a-inherit-logo")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div/input")).sendKeys(photoID);
        driver.findElement(By.xpath("//div/button[@aria-label=\"Search\"]")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div/button[@aria-label=\"Download\"]")).click();

        Thread.sleep(10000);
        driver.findElement(By.xpath("//div/button[@aria-label=\"Buy and download\"]")).click();
        Login("","",driver);
        addInCsv("/home/ehsan/Downloads/PhotoID.cvs",photoID);
        takeScreenShot(driver,"/home/ehsan/Downloads/");
    }



    private static String getPhotoId(WebDriver driver) {
        String imageID = driver.findElement(By.xpath("//div[@class=\"MuiBox-root mui-15jx5nt\"]/p")).getText();
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
    public static void Login(String username, String password,WebDriver driver) throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//a[@data-test-id=\"login-link\"]")).click();
        driver.findElement(By.id("//input[@name=\"username\"]")).sendKeys(username);
        driver.findElement(By.xpath("//input[@data-test-id=\"password-input\"]")).sendKeys(password);
        driver.findElement(By.xpath("//button[@data-test-id=\"login-form-submit-button\"]")).click();
    }
    static public void  takeScreenShot(WebDriver driver,String Path){
        try {

            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path = Path + source.getName();
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e) {
            System.out.println(e.toString());
        }

    }
    public static void addInCsv(String csvFilePath,String id){


        // Check if the file exists, create it if not
        Path path = Paths.get(csvFilePath);

        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))) {

            // Writing data to CSV file
            String[] record1 = {id};


            // Writing individual records
            writer.writeNext(record1);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
