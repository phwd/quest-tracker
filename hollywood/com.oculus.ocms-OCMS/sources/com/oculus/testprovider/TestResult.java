package com.oculus.testprovider;

import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;

public class TestResult<T> {
    @Nullable
    private Exception mError;
    private CountDownLatch mLatch;
    @Nullable
    private T mValue;

    public TestResult(CountDownLatch countDownLatch) {
        this.mLatch = countDownLatch;
    }

    public void setResult(@Nullable T t) {
        this.mValue = t;
        this.mLatch.countDown();
    }

    public void setError(Exception exc) {
        if (exc != null) {
            this.mError = exc;
            this.mLatch.countDown();
            return;
        }
        throw new IllegalArgumentException("exception must be supplied");
    }

    public boolean isError() {
        return this.mError != null;
    }

    @Nullable
    public Exception getError() {
        return this.mError;
    }

    @Nullable
    public T getResult() {
        return this.mValue;
    }
}
