package com.facebook.acra.anr;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ANRDetectorRunnable implements Runnable {
    public volatile int mTick = 0;

    public synchronized int getTick() {
        return this.mTick;
    }

    public synchronized void run() {
        this.mTick = (this.mTick + 1) % 10;
    }
}
