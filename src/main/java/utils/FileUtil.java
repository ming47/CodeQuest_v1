package utils;

import java.io.File;
import java.util.UUID;

public class FileUtil {
	public static final String SAVE_PATH = "C:/file/image/";
	
	public static String getPath(String inputType, String requestPath) {
		String path = SAVE_PATH + requestPath + "/" + inputType +  "/" + TimeUtil.getFileDate();
		
		File file = new File(path);
		file.mkdir();
		
		return file.getAbsolutePath();
	}
	
	public static String getPath(String absolutePath) {
		return "/image/" + absolutePath.substring(SAVE_PATH.length());
	}
	
	public static String generateSysFileName(String originalFileName) {
		UUID uuid = UUID.randomUUID();
		String[] uuids = uuid.toString().split("-");
		
		return uuids[0] +  "_" + originalFileName;
	}
}
