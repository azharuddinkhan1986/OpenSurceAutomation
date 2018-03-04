package org.tcoe.scn.resources;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.tcoe.scn.base.TestBase;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends TestBase{
	private static ExtentReports extent;
	public static ExtentReports getInstance()
	{   Date dNow = new Date( );
	    SimpleDateFormat ft = new SimpleDateFormat ("dd:MMM:yyyy:HH:mm:ss");
	    String file=ft.format(dNow);
	    String filename=file.toString().replace(":","_").replace(" ","_")+".html";
	    filename=CONFIG.getProperty("Browser")+"_Browser_"+CONFIG.getProperty("Env")+"_Automation_Report_"+filename;
	if(extent==null)
	{   
		extent=new ExtentReports(System.getProperty("user.dir")+"//TestReports//"+filename,true);
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		//optional
		extent.addSystemInfo("Selenium","3.x");
		extent.addSystemInfo("Environment",CONFIG.getProperty("Env"));
	}
	
	return extent;
	
	}

/*
	public static void main(String[] args) {
//		Calendar cal = Calendar.getInstance();
//		System.out.println(new SimpleDateFormat("MMM").format(cal.getTime()));
		
		 Date dNow = new Date( );
		    SimpleDateFormat ft = new SimpleDateFormat ("dd:MM:yyyy:hh:mm");
		    String file=ft.format(dNow);
		    String filename=file.toString().replace(":","_").replace(" ","_")+".html";
		    String envrironment=CONFIG.getProperty("Env");
		    String browser=CONFIG.getProperty("Browser")+"_Browser";
		    filename=browser+envrironment+"ExecutionReport"+filename;
		    System.out.println(filename);
	}*/
}
