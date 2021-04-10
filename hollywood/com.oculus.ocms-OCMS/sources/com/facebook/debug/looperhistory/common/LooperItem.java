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

    LooperItem(String str) {
        this.mMsg = str.length() > 21 ? str.substring(21) : str;
    }

    /* access modifiers changed from: package-private */
    public void finish() {
        this.mEndTime = SystemClock.uptimeMillis();
    }

    /* access modifiers changed from: package-private */
    public boolean exceedsThreshold(long j) {
        long j2 = this.mEndTime;
        if (j2 != -1 && j2 - this.mStartTime > j) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isAlive(long j) {
        return this.mEndTime > j;
    }

    /* access modifiers changed from: package-private */
    public void dumpToLogCat(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(": ");
        sb.append(this.mMsg);
        if (this.mEndTime != -1) {
            sb.append(", spent=");
            sb.append(this.mEndTime - this.mStartTime);
        } else {
            sb.append(", running=");
            sb.append(SystemClock.uptimeMillis() - this.mStartTime);
        }
        sb.append("ms");
        Log.d(TAG, sb.toString());
    }
}
