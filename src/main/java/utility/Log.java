package utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    // Initialize Log4j logs
    private static Logger Log= LogManager.getLogger(utility.Log.class.getName());

    public static void startTestCase(String startedTestCaseName){
        Log.info("*****************************************************************************************");
        Log.info("$$$ " + startedTestCaseName + " has been started $$$");
        Log.info("*****************************************************************************************");
    }

    public static void endTestCase(String endedTestCaseName){
        Log.info("*****************************************************************************************");
        Log.info("∆∆∆ " + endedTestCaseName + " has been successfully completed ∆∆∆");
        Log.info("*****************************************************************************************");
    }

    public static void info(String message) {

        Log.info(message);

    }
}
