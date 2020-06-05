package util;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class Hooks {
	private static WebDriver driver;

	@Before
	public synchronized static WebDriver openBrowser() {
		String browser = System.getProperty("BROWSER");
		if (driver == null) {
			try {
				if (browser == null) {
					browser = System.getenv("BROWSER");
					if (browser == null) {
						browser = "chrome";

						// don't know why chromedriver.exe can not run independently in windows 10
					}
				}
				switch (browser) {
				case "chrome":
					ChromeOptions options = new ChromeOptions();
					options.addArguments("enable-automation");
					options.addArguments("--headless");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-extensions");
					options.addArguments("--dns-prefetch-disable");
					options.addArguments("--disable-gpu");
					options.addArguments("disable-features=NetworkService");

					options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

					System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER);
					driver = new ChromeDriver(options);
					break;
				case "ie":
					System.setProperty("webdriver.ie.driver", Constants.IE_DRIVER);
					driver = new InternetExplorerDriver();
					break;
				case "firefox":
					System.setProperty("webdriver.gecko.driver", Constants.FIREFOX_DRIVER);

					FirefoxOptions fireFoxOption = new FirefoxOptions();

					driver = new FirefoxDriver(fireFoxOption);
					break;
				default:
				}
			} catch (UnreachableBrowserException e) {
				driver = new ChromeDriver();
			} catch (WebDriverException e) {
				driver = new ChromeDriver();
			} finally {
				Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
			}
		}
		return driver;
	}

	public static List<String[]> getUrlList() {
		Workbook workBook = ExcelUtility.openWorkbook(Constants.EXCEL_CONFIG);
		List<String[]> excelRows = ExcelUtility.getUrlFromWorkBook(workBook, Constants.URL_SHEET_NAME);

		ExcelUtility.closeWorkbook(workBook);

		return excelRows;
	}

	public static List<String[]> getDeviceTypeConfigList() {
		Workbook workBook = ExcelUtility.openWorkbook(Constants.EXCEL_CONFIG);
		List<String[]> excelRows = ExcelUtility.getDeviceConfigFromWorkBook(workBook, Constants.DEVICE_SHEET_NAME);

		ExcelUtility.closeWorkbook(workBook);

		return excelRows;
	}

	public static void close() {
		try {
			if (driver != null) {
				openBrowser().quit();
			}
		} catch (UnreachableBrowserException e) {
			System.out.println("Can not close browser");
		}
	}

	private static class BrowserCleanup implements Runnable {
		public void run() {
			close();
		}
	}

}