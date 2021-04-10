package com.facebook.acra.anr;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ANRDetectorRunnable implements Runnable {
    private volatile int mTick = 0;

    public synchronized void run() {
        this.mTick = (this.mTick + 1) % 10;
    }

    public synchronized int getTick() {
        return this.mTick;
    }
}
