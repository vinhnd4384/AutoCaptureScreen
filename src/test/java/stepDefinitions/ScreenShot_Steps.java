package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import action.page.PageManageDriver_Actions;
import action.page.ScreenShot_Actions;
import cucumber.api.java.en.Given;
import util.Hooks;

public class ScreenShot_Steps {
	WebDriver driver;
	List<String[]> urlList;

	private ScreenShot_Actions screenshot_action;
	String parent;

	public ScreenShot_Steps() {
		driver = Hooks.openBrowser();
		screenshot_action = PageManageDriver_Actions.getScreenShot(driver);
	}

	@Given("^screenshot \"([^\"]*)\"$")
	public void screenshot(String filewithpath) throws IOException {
		screenshot_action.screenFullScreenWeb(filewithpath);
	}

	@Given("^Choose site to capture \"([^\"]*)\"$")
	public void choose_site_to_capture_something(String site) throws Throwable {
		screenshot_action.OpenSite(site);
		// Thread.sleep(5000);
	}

	@Given("^Close popup$")
	public void close_popup() throws Throwable {
		screenshot_action.ClosePopUp();
	}

	@Given("^Capture on responsive with length \"([^\"]*)\",width \"([^\"]*)\" and device type \"([^\"]*)\" with file path \"([^\"]*)\"$")
	//@Given("^Capture on responsive with length \"([^\"]*)\" and width \"([^\"]*)\" with file path \"([^\"]*)\"$")
	public void capture_on_responsive_with_length_something_and_width_something(int length, int Width,
			String deviceType, String filewithpath) throws Throwable {
		screenshot_action.screenShotFullSreenResponsiveWeb(length, Width, deviceType, filewithpath);
	}

}
