package com.facebook.mobileconfig.impl;

import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.atomic.AtomicIntegerArray;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExposureRateLimiter {
    private final AtomicIntegerArray mLoggingSlotIds;

    public ExposureRateLimiter(int i) {
        this.mLoggingSlotIds = new AtomicIntegerArray(i);
    }

    public boolean take(int i) {
        return this.mLoggingSlotIds.compareAndSet(i, 0, 1);
    }

    public void clear() {
        int length = this.mLoggingSlotIds.length();
        for (int i = 0; i < length; i++) {
            this.mLoggingSlotIds.set(i, 0);
        }
    }
}
