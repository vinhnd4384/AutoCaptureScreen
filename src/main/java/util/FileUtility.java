package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FileUtility {
	private FileUtility() {
	}

	public static String createFolder(String folderPath) {
		Path path = Paths.get(folderPath);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				return Files.createDirectories(path).toAbsolutePath().toString();
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static String createFolder_Automatically_YYYYMMDDHHMMSS(String parentFolderPath)
	{
		Date date = Calendar.getInstance().getTime();  
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd_HHmm");
		String folderPath = parentFolderPath + "\\" + f.format(date);
		
		return createFolder(folderPath);
	}
}
