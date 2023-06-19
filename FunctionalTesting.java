package com.actitime.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class FunctionalTesting {


	static
	{
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
	}

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("https://demo.dealsdray.com/");  
		driver.manage().window().maximize();
		driver.findElement(By.xpath("(//input)[1]")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("(//input)[2]")).sendKeys("prexo.mis@dealsdray.com");
		driver.findElement(By.xpath("//button[.='Login']")).click();
		driver.findElement(By.xpath("(//div)[12]/../div[2]/button/div[2]/span")).click();
		driver.findElement(By.xpath("(//div)[16]")).click();
		driver.findElement(By.xpath("//button[.='Add Bulk Orders']")).click();
		File f=new File("./data/demo-data.xlsx");
		String input=f.getAbsolutePath();
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(input);
		driver.findElement(By.xpath("//button[normalize-space()='Import']")).click();
		driver.findElement(By.xpath("//button[.='Validate Data']")).click();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		driver.manage().window().maximize();
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage() , "PNG",new File(System.clearProperty("user.dir")+"\\Screenshot\\fullpage.png"));
		driver.close();
	}
}
