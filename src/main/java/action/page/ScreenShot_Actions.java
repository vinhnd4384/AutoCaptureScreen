package action.page;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import action.common.Abstract_Actions;
import util.Constants;

public class ScreenShot_Actions extends Abstract_Actions {
	WebDriver driver;

	public ScreenShot_Actions(WebDriver mapdriver) {
		driver = mapdriver;
	}

	public void screenFullScreenWeb(String filewithpath) throws IOException {
		screenShotFullSreenRegularWeb(driver, filewithpath);
	}

	public void OpenSite(String URL) {
		openUrl(driver, URL);
	}

	public void ClosePopUp() {

		WebElement isExistElement = waitForVisible(driver, Constants.POP_UP_QC);

		// if site don't not popup.
		if (isExistElement != null) {
			clicktoElement(driver, Constants.POP_UP_QC);
		}
	}

	public void screenShotFullSreenResponsiveWeb(int length, int width, String deviceType, String filewithpath)
			throws InterruptedException, IOException {
		setWindowSizeFollowResponsiveDevice(driver, length, width);
		
		scroll(driver, length, width, deviceType, filewithpath);
	}

}