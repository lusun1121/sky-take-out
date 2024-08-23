package com.sky.constant;

import java.io.File;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class LocalUploadConstant {
	
	public static final String LOCAL_UPLOAD_DIR = directoryExists();
    
	public static String directoryExists() {
    	String filePath = "D:/images/final_project";
    	File file = new File(filePath);
    	if (!file.exists()) {
    		if (file.mkdirs()) {
    			log.info("Directory created: {}", filePath);
    			return filePath;
    		} else {
    			log.error("Failed to create directory: {}", filePath);
    			return null;
	        }
    	}else {
    		log.info("Directory already exists: {}",filePath);
    	}
    	return filePath;
    }
    

	
}
