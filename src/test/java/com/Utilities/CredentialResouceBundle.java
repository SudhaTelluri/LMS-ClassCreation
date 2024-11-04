package com.Utilities;

import java.util.ResourceBundle;

public class CredentialResouceBundle {
	public static ResourceBundle rb = ResourceBundle.getBundle("Config/config");

	public static String getBrowser() {
		return rb.getString("browser");
	}

	public static String getURL() {
		return rb.getString("url");
	}

	public static String getUsername() {
		return rb.getString("username");
	}
	
	public static String getPassword() {
		return rb.getString("password");
	}
	
	public static String getBatchName() {
		return rb.getString("batchName");
	}
	public static String getClassTopic() {
		return rb.getString("classTopic");
	}

}
