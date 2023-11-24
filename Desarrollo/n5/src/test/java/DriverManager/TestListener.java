package DriverManager;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TestListener extends ExecutionContext implements ITestListener {

    private CustomScreenRecorder screenRecorder;
    public TestListener() {
        try {
            //this is the location that we are going to save the recorded file
            screenRecorder = new CustomScreenRecorder(new File(System.getProperty("user.dir") + "/src/test/resources"));

        } catch (IOException | AWTException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void onTestStart(ITestResult iTestResult) {

        try {
            if(System.getenv("ENTORNO").equals("local")){
                screenRecorder.startRecording(THREAD_LOCAL_TEST_RESULT.getClass().getSimpleName() + "-" + THREAD_LOCAL_TEST_RESULT.getMethod().getMethodName() + "_" +
                        date  , true);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test PASSED " + THREAD_LOCAL_TEST_RESULT.getTestClass().getName() + " - " + THREAD_LOCAL_TEST_RESULT.getMethod().getMethodName());
        stopScreenRecording(true);
    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test FAILED " + THREAD_LOCAL_TEST_RESULT.getTestClass().getName() + " - " + THREAD_LOCAL_TEST_RESULT.getMethod().getMethodName());
        stopScreenRecording(true);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test SKIPPED " + THREAD_LOCAL_TEST_RESULT.getTestClass().getName() + " - " + THREAD_LOCAL_TEST_RESULT.getMethod().getMethodName());
        stopScreenRecording(true);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }


    private void stopScreenRecording(boolean keepFile) {
        try {
            screenRecorder.stopRecording(keepFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}