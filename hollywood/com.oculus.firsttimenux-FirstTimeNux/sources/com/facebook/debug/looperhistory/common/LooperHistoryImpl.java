package com.facebook.debug.looperhistory.common;

import android.os.SystemClock;
import android.util.Log;
import java.util.LinkedList;
import javax.annotation.Nullable;

public class LooperHistoryImpl implements ILooperHistory {
    private static final String TAG = "LooperHistory";
    private final long mDuration;
    private final LinkedList<LooperItem> mHistory = new LinkedList<>();
    @Nullable
    private LooperItem mLastMessage;
    private final long mThreshold;

    public LooperHistoryImpl(long duration, long threshold) {
        this.mDuration = duration;
        this.mThreshold = threshold;
    }

    public synchronized void handleMessage(String message) {
        if (message.startsWith(">")) {
            if (this.mLastMessage != null) {
            }
            this.mLastMessage = new LooperItem(message);
        } else if (message.startsWith("<") && this.mLastMessage != null) {
            this.mLastMessage.finish();
            if (this.mLastMessage.exceedsThreshold(this.mThreshold)) {
                this.mHistory.addLast(this.mLastMessage);
            }
            this.mLastMessage = null;
            removeOldMessage();
        }
    }

    private synchronized void removeOldMessage() {
        if (!this.mHistory.isEmpty()) {
            long target = SystemClock.uptimeMillis() - this.mDuration;
            while (!this.mHistory.getFirst().isAlive(target)) {
                this.mHistory.removeFirst();
                if (this.mHistory.isEmpty()) {
                    break;
                }
            }
        }
    }

    @Override // com.facebook.debug.looperhistory.common.ILooperHistory
    public synchronized void dumpToLogCat() {
        if (this.mLastMessage != null || !this.mHistory.isEmpty()) {
            Log.d(TAG, "=== Messages execute more than " + this.mThreshold + "ms during the past " + this.mDuration + "ms ===");
            if (this.mLastMessage != null) {
                this.mLastMessage.dumpToLogCat("last");
            }
            for (int i = this.mHistory.size() - 1; i >= 0; i--) {
                this.mHistory.get(i).dumpToLogCat(String.valueOf(i));
            }
        }
    }
}
