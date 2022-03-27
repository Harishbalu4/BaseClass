package org.baseclass;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Sample extends HelperClass {

	public static void main(String[] args) throws InterruptedException, IOException {

		launchChromeBrowser();
		launchUrl("https://www.facebook.com/");
		togetTitle();
		getUrl();
		WebElement user = driver.findElement(By.id("email"));
		filltxtbox(user, readFromExcel("Sheet1", 1, 2));

		WebElement password = driver.findElement(By.id("pass"));
		filltxtbox(password, readFromExcel("Sheet1", 1, 3));

		tohold(5000);

		WebElement btn = driver.findElement(By.name("login"));
		btnclk(btn);
		
		toclose();
		

	}

}
