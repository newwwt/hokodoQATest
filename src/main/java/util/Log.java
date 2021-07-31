package util;

import org.testng.Reporter;

public class Log {

    public static void info(String message) {
        Reporter.log(message, true);
    }
}
