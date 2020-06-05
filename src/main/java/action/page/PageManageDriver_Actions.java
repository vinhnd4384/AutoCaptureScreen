package action.page;

import org.openqa.selenium.WebDriver;

public class PageManageDriver_Actions {

	private static ScreenShot_Actions screenShotAction;
	public static ScreenShot_Actions getScreenShot(WebDriver driver) {
		
		if(screenShotAction == null)
		{
			screenShotAction = new ScreenShot_Actions(driver);
		}
		
		return screenShotAction;
	}	
}
