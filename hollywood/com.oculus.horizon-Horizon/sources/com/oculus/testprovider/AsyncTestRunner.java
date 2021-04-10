package com.oculus.testprovider;

import java.util.concurrent.CountDownLatch;

public class AsyncTestRunner<T> {
    public final CountDownLatch mLatch;
    public final TestRunnable<T> mRunnable;
    public final TestResult<T> mTestResult;

    public final TestResult<T> A00() {
        try {
            this.mRunnable.A8Q(this.mTestResult);
        } catch (Exception e) {
            TestResult<T> testResult = this.mTestResult;
            testResult.mError = e;
            testResult.mLatch.countDown();
        }
        try {
            this.mLatch.await();
        } catch (InterruptedException e2) {
            TestResult<T> testResult2 = this.mTestResult;
            testResult2.mError = e2;
            testResult2.mLatch.countDown();
        }
        return this.mTestResult;
    }

    public AsyncTestRunner(TestRunnable<T> testRunnable) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mLatch = countDownLatch;
        this.mTestResult = new TestResult<>(countDownLatch);
        this.mRunnable = testRunnable;
    }
}
