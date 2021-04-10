package com.oculus.testprovider;

public interface TestRunnable<T> {
    void run(TestResult<T> testResult);
}
