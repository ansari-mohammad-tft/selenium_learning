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

        driver.findElement(By.xpath("//div[3]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[2]/div[3]/div/div/div")).click();

        uploadFile(driver,"/home/ehsan/Downloads/download.jpeg");

        driver.findElement(By.className("mui-t7xql4-a-inherit-link")).click();
        scrollDown(driver);
        String photoID = getPhotoId(driver);
        addInCsv("/home/ehsan/Downloads/PhotoID.cvs",photoID);
        scrollUp(driver);
        driver.findElement(By.className("mui-o90xvh-a-inherit-logo")).click();

        driver.findElement(By.xpath("//div[1]/div[3]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[2]/div[2]/div[2]/div/div/input")).sendKeys(photoID);
        driver.findElement(By.xpath("//div[1]/div[3]/div/div[2]/div[1]/div/div[1]/div/div[1]/div[2]/div[3]/button")).click();

        driver.findElement(By.xpath("//div[1]/div[3]/div/div/div[1]/div[2]/div[1]/div/div/div[1]/div[1]/div/div[2]/div/button")).click();


        driver.findElement(By.xpath("//div[12]/div[3]/div[2]/div[2]/button")).click();
        Login("","",driver);
        addInCsv("/home/ehsan/Downloads/PhotoID.cvs",photoID);
        takeScreenShot(driver,"/home/ehsan/Downloads/");
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
