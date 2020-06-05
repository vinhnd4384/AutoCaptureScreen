package action.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import util.Constants;

public class Abstract_Actions {

	private final static Logger logger = Logger.getLogger(Abstract_Actions.class);

	/*
	 * sự kiện hower chuột
	 * 
	 */
	// open url
	public void OpenURL(WebDriver driver, String url) {
		driver.get(url);
	}

	public void setWindowSizeFollowResponsiveDevice(WebDriver driver, int length, int width) {
		Dimension d = new Dimension(width, length);
		driver.manage().window().setSize(d);
	}

	public void scroll(WebDriver driver, int length, int width, String deviceType, String filewithpath)
			throws InterruptedException, IOException {

		JavascriptExecutor javScriptExecutor = (JavascriptExecutor) driver;

		// set scroll to TOP
		javScriptExecutor.executeScript("window.scrollTo(0, 0);");

		// get scrollbar's length
		long windowHeight = (long) javScriptExecutor.executeScript("return window.innerHeight");
		long pageHeight = (long) javScriptExecutor.executeScript("return document.body.scrollHeight");

		int count = 0;
		StringBuilder temp = new StringBuilder();
		for (int i = 1; i < 20; i++) {

			Thread.sleep(5000);
			TakesScreenshot scrShot = ((TakesScreenshot) driver);
			File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

			temp.append(filewithpath);
			temp.append(deviceType);
			temp.append("_");
			temp.append(i);
			temp.append(".jpg");
			File DestFile = new File(temp.toString());

			// Copy file at destination
			FileUtils.copyFile(SrcFile, DestFile);

			temp.setLength(0);

			// Next scroll
			javScriptExecutor.executeScript("window.scrollBy(0, " + windowHeight + ");");

			// Some websites load new content automatically when scrolling down. In such
			// case, page Height is to be revised after each scrolling down
			windowHeight = (long) javScriptExecutor.executeScript("return window.innerHeight");
			pageHeight = (long) javScriptExecutor.executeScript("return document.body.scrollHeight");

			count = count + 1;
			if (count * windowHeight > pageHeight) {
				break;
			}
		}
	}

	// open url
	public void openUrl(WebDriver driver, String URL) {
		driver.get(URL);

	}

	// ham getTitle
	public String getTitle(WebDriver driver) {

		return driver.getTitle();
	}

	// ham get url hien tai
	public String getcurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	// Ham back
	public void back(WebDriver driver) {
		driver.navigate().back();

	}

	// ham forward
	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	// ham refresh
	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	// ham click vao dynamic locator
	public void clicktoElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		// System.out.println("----- Click to dynamic element = " + locator + "
		// ------");
		WebElement element = driver.findElement(By.xpath(locator));
		// element.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	/*
	 * ham voi alert
	 */
	// accept alert
	public void acceptAlert(WebDriver driver, String username, String password, String url) {
		try {
			// refer Percent encoding in case password have special character :
			// https://en.wikipedia.org/wiki/Percent-encoding
			String URL = "http://" + username + ":" + URLEncoder.encode(password, "UTF-8") + "@"
					+ url.replace("http://", "");

			driver.get(URL);

			logger.info(URL);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();

			Thread.sleep(4000);
		} catch (Exception e) {
		}

	}

	// cancel alert
	public void canceltAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();

	}

	// get text alert
	public String getTexttAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert.getText();

	}

	public WebElement waitForVisible(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		WebDriverWait wait = new WebDriverWait(driver, 60);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchToChildWindow(WebDriver driver, String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	// ham close window ngoai tru cua so cha
	public boolean closeAllWithoutParentWindows(WebDriver driver, String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		// Get all current windows/ tabs
		Set<String> allWindows = driver.getWindowHandles();

		// Lặp từng window/ tab
		for (String runWindows : allWindows) {
			System.out.println("Window ID = " + runWindows);
			// Switch vào từng window trước
			driver.switchTo().window(runWindows);

			// Get ra title của window/ tab mà mình đã switch qa
			String actualTitle = driver.getTitle();

			// Kiểm tra nếu title mình đã get mà bằng vs expected title mình truyỿn vào
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void scrollToElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[]) value);
		By by = By.xpath(locator);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void screenShotFullSreenRegularWeb(WebDriver driver, String filepath) throws IOException {

		String subFilePath = filepath + "\\" + Constants.REGULAR_WEB_FOLDER_NAME + ".JPG";

		// Full window
		driver.manage().window().maximize();

		AShot aShot = new AShot();
		final Screenshot screenshot = aShot.shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);
		try {

			final BufferedImage image = screenshot.getImage();
			ImageIO.write(image, "JPG", new File(subFilePath));
		} catch (IOException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
