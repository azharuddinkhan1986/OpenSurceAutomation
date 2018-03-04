package org.tcoe.scn.utility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ErrorUtil {
	private static Map<ITestResult,List> verificationFailuresMap = new HashMap<ITestResult,List>();
	private static Map<ITestResult,List> skipMap = new HashMap<ITestResult,List>();

	
	     public static void addVerificationFailure(Throwable e) {
				List verificationFailures = getVerificationFailures();
				verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
				verificationFailures.add(e);
				System.out.println(verificationFailures);
			}
		  
		  public static List getVerificationFailures() {
				List verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
				return verificationFailures == null ? new ArrayList() : verificationFailures;
			}

}


