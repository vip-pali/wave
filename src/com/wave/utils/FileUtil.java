package com.wave.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


import org.apache.log4j.LogManager;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.log4j.Logger;

public class FileUtil {

	private static Properties uploadProperties;
	private static Logger logger = LogManager.getLogger(FileUtil.class);

	static {
		try {

			InputStreamReader uploadPropertiesIS = new InputStreamReader(FileUtil.class.getResourceAsStream("/upload.properties"));
			uploadProperties = new Properties();
			uploadProperties.load(uploadPropertiesIS);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static String getSurveyImages() {
		return uploadProperties.getProperty("SurveyImages");
	}
	


}
