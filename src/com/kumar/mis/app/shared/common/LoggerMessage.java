package com.kumar.mis.app.shared.common;

import com.google.gwt.core.client.GWT;

public class LoggerMessage {
	
	public  static boolean isConsolePrintingEnabled = false;
	
	public static void printToConsole(String message){
		
		if(isConsolePrintingEnabled)
			System.out.println(message);
		
		GWT.log(message);
	}

}
