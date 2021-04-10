package com.facebook.errorreporting.appstate;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppStateForegroundTime {
    private long mFgCheckTimeStamp = 0;
    private long mFgCount;
    private boolean mIsBackgrounded;
    private long mTotalFgMillis = 0;

    public AppStateForegroundTime(boolean z) {
        long j = 0;
        this.mIsBackgrounded = z;
        this.mFgCheckTimeStamp = SystemClock.uptimeMillis();
        this.mFgCount = !z ? 1 : j;
    }

    public synchronized long totalForegroundTimeMillis() {
        return this.mTotalFgMillis + currentForegroundTimeMillis();
    }

    public synchronized long currentForegroundTimeMillis() {
        return this.mIsBackgrounded ? 0 : SystemClock.uptimeMillis() - this.mFgCheckTimeStamp;
    }

    public synchronized long foregroundCount() {
        return this.mFgCount;
    }

    public synchronized void update(boolean z) {
        boolean z2 = true;
        boolean z3 = this.mIsBackgrounded && !z;
        if (this.mIsBackgrounded || !z) {
            z2 = false;
        }
        if (z2) {
            this.mTotalFgMillis += SystemClock.uptimeMillis() - this.mFgCheckTimeStamp;
        } else if (z3) {
            this.mFgCheckTimeStamp = SystemClock.uptimeMillis();
            this.mFgCount++;
        }
        this.mIsBackgrounded = z;
    }

    public synchronized boolean inBackground() {
        return this.mIsBackgrounded;
    }
}
