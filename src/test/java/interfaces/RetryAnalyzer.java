package interfaces;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    // How often should failed tests be rerun?
    final int retryLimit = 1;


    int counter = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(counter < retryLimit)
        {
            counter++;
            return true;
        }
        return false;
    }
}
