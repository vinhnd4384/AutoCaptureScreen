package main;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.WebDriver;

import action.page.PageManageDriver_Actions;
import action.page.ScreenShot_Actions;
import util.Constants;
import util.ExcelUtility;
import util.FileUtility;
import util.Hooks;

public class CaptureMain {

	private final static Logger logger = Logger.getLogger(CaptureMain.class);
	WebDriver driver;
	List<String[]> urlList;
	List<String[]> deviceTypeList;

	private ScreenShot_Actions screenshot_action;
	String parent;

	public static void main(String[] args) {
		logger.info(" Start!! ");
		new CaptureMain().captureWebPage();
		logger.info(" Completed!! ");
	}

	public CaptureMain() {
		driver = Hooks.openBrowser();
		screenshot_action = PageManageDriver_Actions.getScreenShot(driver);

		logger.info("------START READ EXCEL-----");
		// Read excel config file.
		Workbook workBook = ExcelUtility.openWorkbook(Constants.EXCEL_CONFIG);
		urlList = ExcelUtility.getUrlFromWorkBook(workBook, Constants.URL_SHEET_NAME);
		deviceTypeList = ExcelUtility.getDeviceConfigFromWorkBook(workBook, Constants.DEVICE_SHEET_NAME);
		ExcelUtility.closeWorkbook(workBook);
		logger.info("------END READ EXCEL-----");

	}

	public void captureWebPage() {

		String rootFolder = FileUtility
				.createFolder_Automatically_YYYYMMDDHHMMSS(deviceTypeList.get(0)[Constants.ROOT_FOLDER_PATH_EXCEL]);
		logger.info("Image will be output in folder: " + rootFolder);

		for (String[] url : urlList) {

			if (!Boolean.valueOf(url[Constants.IS_CAPTURE])) {
				continue;
			}
			
			// in case need to input username / password in alert window.
			if (!"".equals(url[Constants.USERNAME_COL_EXCEL].trim())) {
				screenshot_action.acceptAlert(driver, url[Constants.USERNAME_COL_EXCEL],
						url[Constants.PASSWORD_COL_EXCEL], url[Constants.URL_COL_EXCEL]);
			} else {
				driver.get(url[Constants.URL_COL_EXCEL]);
			}

			String subFilePath = rootFolder + "\\" + url[Constants.NAME_COL_EXCEL] + "\\";
			FileUtility.createFolder(subFilePath);

			try {
				screenshot_action.ClosePopUp();
				logger.info(url[Constants.NAME_COL_EXCEL] + " : Closed initilized popup");
			} catch (Exception e) {
				logger.warn("Websites do not have initilized popup");
			}

			for (String[] deviceType : deviceTypeList) {
				if (!Boolean.valueOf(deviceType[Constants.IS_CAPTURE_DEVICE_TYPE_EXCEL])) {
					continue;
				}

				try {
					screenshot_action.screenShotFullSreenResponsiveWeb(
							Integer.parseInt(deviceType[Constants.HEIGHT_DEVICE_TYPE_EXCEL]),
							Integer.parseInt(deviceType[Constants.WIDTH_DEVICE_TYPE_EXCEL]),
							deviceType[Constants.DEVICE_TYPE_EXCEL], subFilePath);
					logger.info(url[Constants.NAME_COL_EXCEL] + " : Capture responsive device : DONE");

				} catch (Exception e) {
					logger.error(e.getMessage());
					continue;
				}
			}

			try {
				screenshot_action.screenFullScreenWeb(subFilePath);
				logger.info(url[Constants.NAME_COL_EXCEL] + " : Capture regular sites : DONE");
			} catch (Exception e) {
				logger.error(e.getMessage());
				continue;
			}
		}

		Hooks.close();
	}
}