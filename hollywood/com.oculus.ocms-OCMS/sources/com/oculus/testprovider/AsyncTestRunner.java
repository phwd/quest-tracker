package com.oculus.testprovider;

import java.util.concurrent.CountDownLatch;

public class AsyncTestRunner<T> {
    private final CountDownLatch mLatch = new CountDownLatch(1);
    private final TestRunnable<T> mRunnable;
    private final TestResult<T> mTestResult = new TestResult<>(this.mLatch);

    public AsyncTestRunner(TestRunnable<T> testRunnable) {
        this.mRunnable = testRunnable;
    }

    public TestResult<T> run() {
        try {
            this.mRunnable.run(this.mTestResult);
        } catch (Exception e) {
            this.mTestResult.setError(e);
        }
        try {
            this.mLatch.await();
        } catch (InterruptedException e2) {
            this.mTestResult.setError(e2);
        }
        return this.mTestResult;
    }
}
