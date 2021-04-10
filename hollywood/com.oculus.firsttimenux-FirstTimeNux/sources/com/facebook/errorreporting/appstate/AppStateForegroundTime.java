package com.facebook.errorreporting.appstate;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppStateForegroundTime {
    private long mFgCheckTimeStamp = 0;
    private long mFgCount;
    private boolean mIsBackgrounded;
    private long mTotalFgMillis = 0;

    public AppStateForegroundTime(boolean startsInBackground) {
        long j = 0;
        this.mIsBackgrounded = startsInBackground;
        this.mFgCheckTimeStamp = SystemClock.uptimeMillis();
        this.mFgCount = !startsInBackground ? 1 : j;
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

    public synchronized void update(boolean isInBackground) {
        boolean transitionedToForeground;
        boolean transitionedToBackground = true;
        synchronized (this) {
            if (!this.mIsBackgrounded || isInBackground) {
                transitionedToForeground = false;
            } else {
                transitionedToForeground = true;
            }
            if (this.mIsBackgrounded || !isInBackground) {
                transitionedToBackground = false;
            }
            if (transitionedToBackground) {
                this.mTotalFgMillis += SystemClock.uptimeMillis() - this.mFgCheckTimeStamp;
            } else if (transitionedToForeground) {
                this.mFgCheckTimeStamp = SystemClock.uptimeMillis();
                this.mFgCount++;
            }
            this.mIsBackgrounded = isInBackground;
        }
    }

    public synchronized boolean inBackground() {
        return this.mIsBackgrounded;
    }
}
