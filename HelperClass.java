package org.baseclass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HelperClass {

	public static WebDriver driver;
	public static Actions a;
	public static Robot r;
	public static Alert al;
	public static JavascriptExecutor js;

	public static void launchChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

	}

	public static void launchUrl(String url) {
		driver.get(url);

	}

	public static void maximize() {
		driver.manage().window().maximize();
	}

	public static void getUrl() {
		String url = driver.getCurrentUrl();
		System.out.println("url:" + url);
	}

	public static void togetTitle() {
		String title = driver.getTitle();
		System.out.println("Title:" + title);
	}

	public static void filltxtbox(WebElement ref, String val) {
		ref.sendKeys(val);

	}

	public static void btnclk(WebElement ref) {
		ref.click();

	}

	public static void toclose() {
		driver.close();

	}

	public static void toquit() {
		driver.quit();
	}

	public static void tohold(int time) throws InterruptedException {
		Thread.sleep(time);

	}

	public static void torefresh() {
		driver.navigate().refresh();
	}

	public static void copy() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_C);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_C);

	}

	public static void paste() throws AWTException {
		r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_V);

	}

	public static void rightClick(WebElement ref) {
		a = new Actions(driver);
		a.contextClick(ref).perform();

	}

	public static void doubleClick(WebElement ref) {
		a = new Actions(driver);
		a.doubleClick(ref).perform();

	}

	public static void screenshot(String picname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("C:\\Users\\HARI\\eclipse-workspace\\Maven\\snap\\" + picname + ".png");
		FileUtils.copyFile(src, des);
	}

	public static void moveToElement(WebElement ref) {
		a.moveToElement(ref).perform();

	}

	public static void dragAndDrop(WebElement src, WebElement des) {
		a.dragAndDrop(src, des).perform();

	}

	public static void accept() {
		al = driver.switchTo().alert();
		al.accept();

	}

	public static void dismiss() {
		al = driver.switchTo().alert();
		al.dismiss();

	}

	public static void scrollDown(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeAsyncScript("arguments[0].scrollIntiView(true)", ref);

	}

	public static void scrollUp(WebElement ref) {
		js = (JavascriptExecutor) driver;
		js.executeAsyncScript("arguments[0].scrollIntiView(false)", ref);

	}

	public static String readFromExcel(String sheetName, int rowNo, int cellNo) throws IOException {

		File f = new File("C:\\Users\\HARI\\eclipse-workspace\\Maven\\xcel\\Sample1.xlsx");

		FileInputStream Fin = new FileInputStream(f);

		Workbook book = new XSSFWorkbook(Fin);

		Sheet sh = book.getSheet(sheetName);

		Row r = sh.getRow(rowNo);

		Cell c = r.getCell(cellNo);

		int type = c.getCellType();

		String input;

		if (type == 1) {

			input = c.getStringCellValue();

		} else if (DateUtil.isCellDateFormatted(c)) {

			Date da = c.getDateCellValue();

			SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");

			input = sd.format(da);

		} else {

			double d = c.getNumericCellValue();

			long l = (long) d;

			input = String.valueOf(l);
		}

		return input;

	}

}
