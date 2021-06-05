package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigFile {

	Properties pro;
	
	public ReadConfigFile()
	{
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	public String getExecution()
	{
		String url=pro.getProperty("execution");
		return url;
	}

	
}


