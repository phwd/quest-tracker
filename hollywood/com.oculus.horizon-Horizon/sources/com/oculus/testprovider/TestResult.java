package com.oculus.testprovider;

import java.util.concurrent.CountDownLatch;
import javax.annotation.Nullable;

public class TestResult<T> {
    @Nullable
    public Exception mError;
    public CountDownLatch mLatch;
    @Nullable
    public T mValue;

    public TestResult(CountDownLatch countDownLatch) {
        this.mLatch = countDownLatch;
    }
}
