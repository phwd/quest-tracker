package com.facebook.debug.looperhistory.common;

import android.os.SystemClock;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
class LooperItem {
    private static final String TAG = "LooperHistory";
    private long mEndTime = -1;
    private final String mMsg;
    private final long mStartTime = SystemClock.uptimeMillis();

    LooperItem(String line) {
        this.mMsg = line.length() > 21 ? line.substring(21) : line;
    }

    /* access modifiers changed from: package-private */
    public void finish() {
        this.mEndTime = SystemClock.uptimeMillis();
    }

    /* access modifiers changed from: package-private */
    public boolean exceedsThreshold(long threshold) {
        if (this.mEndTime != -1 && this.mEndTime - this.mStartTime > threshold) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isAlive(long time) {
        return this.mEndTime > time;
    }

    /* access modifiers changed from: package-private */
    public void dumpToLogCat(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(": ").append(this.mMsg);
        if (this.mEndTime != -1) {
            sb.append(", spent=").append(this.mEndTime - this.mStartTime);
        } else {
            sb.append(", running=").append(SystemClock.uptimeMillis() - this.mStartTime);
        }
        sb.append("ms");
        Log.d(TAG, sb.toString());
    }
}
