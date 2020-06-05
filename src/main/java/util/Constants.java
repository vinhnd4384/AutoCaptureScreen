package util;

public class Constants {

	private Constants() {

	}

	public static final String POP_UP_QC = "//div[@class='row']//a[@class='_brlbs-btn _brlbs-btn-accept-all cursor']";

	public static final String REGULAR_WEB_FOLDER_NAME = "regular_web_page";

	public static final String URL_SHEET_NAME = "URL";
	public static final String DEVICE_SHEET_NAME = "Device";

	public static final int NO_COL_EXCEL = 0;
	public static final int GROUP_COL_EXCEL = 1;
	public static final int NAME_COL_EXCEL = 2;
	public static final int URL_COL_EXCEL = 3;
	public static final int IS_CAPTURE = 4;
	public static final int USERNAME_COL_EXCEL = 5;
	public static final int PASSWORD_COL_EXCEL = 6;
	public static final int HAVE_POPUP_COL_EXCEL = 7;
	public static final int NOTE_COL_EXCEL = 8;

	public static final int DEVICE_TYPE_EXCEL = 1;
	public static final int HEIGHT_DEVICE_TYPE_EXCEL = 2;
	public static final int WIDTH_DEVICE_TYPE_EXCEL = 3;
	public static final int IS_CAPTURE_DEVICE_TYPE_EXCEL = 4;
	public static final int ROOT_FOLDER_PATH_EXCEL = 5;

	public static final String CHROME_DRIVER = ".\\webdrivers\\chromedriver.exe";
	public static final String FIREFOX_DRIVER = ".\\webdrivers\\geckodriver.exe";
	public static final String IE_DRIVER = ".\\webdrivers\\IEDriverServer.exe";
	public static final String EXCEL_CONFIG = ".\\Website_List.xlsx";
}
