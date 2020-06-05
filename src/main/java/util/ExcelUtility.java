package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	private ExcelUtility() {}
	public static Workbook openWorkbook(String workBookPath) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(workBookPath));
			return new XSSFWorkbook(excelFile);
		} catch (IOException e) {
			return null;
		}
	}

	public static List<String[]>  getUrlFromWorkBook(Workbook workbook, String sheetName) {
		Sheet datatypeSheet = workbook.getSheet(sheetName);
		Iterator<Row> iterator = datatypeSheet.iterator();

		List<String[]> returnArr = new ArrayList<String[]>();

		// ignore header.
		iterator.next();
		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			
			String[] rowRes = new String[Constants.NOTE_COL_EXCEL + 1];
			rowRes[Constants.NO_COL_EXCEL] = String.valueOf((int)currentRow.getCell(Constants.NO_COL_EXCEL).getNumericCellValue());
			rowRes[Constants.GROUP_COL_EXCEL] = currentRow.getCell(Constants.GROUP_COL_EXCEL).getStringCellValue();
			rowRes[Constants.NAME_COL_EXCEL] = currentRow.getCell(Constants.NAME_COL_EXCEL).getStringCellValue();
			rowRes[Constants.URL_COL_EXCEL] = currentRow.getCell(Constants.URL_COL_EXCEL).getStringCellValue();
			rowRes[Constants.IS_CAPTURE] = String.valueOf(currentRow.getCell(Constants.IS_CAPTURE).getBooleanCellValue());
			rowRes[Constants.USERNAME_COL_EXCEL] = currentRow.getCell(Constants.USERNAME_COL_EXCEL).getStringCellValue();
			rowRes[Constants.PASSWORD_COL_EXCEL] = currentRow.getCell(Constants.PASSWORD_COL_EXCEL).getStringCellValue();
			rowRes[Constants.HAVE_POPUP_COL_EXCEL] = currentRow.getCell(Constants.HAVE_POPUP_COL_EXCEL).getStringCellValue();
			rowRes[Constants.NOTE_COL_EXCEL] = currentRow.getCell(Constants.NOTE_COL_EXCEL).getStringCellValue();
			
			returnArr.add(rowRes);
		}
		
		return returnArr;
	}	
	
	
	public static List<String[]>  getDeviceConfigFromWorkBook(Workbook workbook, String sheetName) {
		Sheet datatypeSheet = workbook.getSheet(sheetName);
		Iterator<Row> iterator = datatypeSheet.iterator();

		List<String[]> returnArr = new ArrayList<String[]>();
		// ignore header.
		iterator.next();
		while (iterator.hasNext()) {

			Row currentRow = iterator.next();
			String[] rowRes = new String[Constants.ROOT_FOLDER_PATH_EXCEL + 1];
			rowRes[Constants.NO_COL_EXCEL] = String.valueOf((int)currentRow.getCell(Constants.NO_COL_EXCEL).getNumericCellValue());
			rowRes[Constants.DEVICE_TYPE_EXCEL] = currentRow.getCell(Constants.DEVICE_TYPE_EXCEL).getStringCellValue();
			rowRes[Constants.HEIGHT_DEVICE_TYPE_EXCEL] = String.valueOf((int)currentRow.getCell(Constants.HEIGHT_DEVICE_TYPE_EXCEL).getNumericCellValue());
			rowRes[Constants.WIDTH_DEVICE_TYPE_EXCEL] = String.valueOf((int)currentRow.getCell(Constants.WIDTH_DEVICE_TYPE_EXCEL).getNumericCellValue());
			rowRes[Constants.IS_CAPTURE_DEVICE_TYPE_EXCEL] = String.valueOf(currentRow.getCell(Constants.IS_CAPTURE_DEVICE_TYPE_EXCEL).getBooleanCellValue());
			rowRes[Constants.ROOT_FOLDER_PATH_EXCEL] = currentRow.getCell(Constants.ROOT_FOLDER_PATH_EXCEL).getStringCellValue();
			
			returnArr.add(rowRes);
		}
		
		return returnArr;
	}	
	
	public static void closeWorkbook(Workbook workbook) {
		try {
			workbook.close();
		} catch (IOException e) {
		}
	}
}
