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

    public LooperHistoryImpl(long j, long j2) {
        this.mDuration = j;
        this.mThreshold = j2;
    }

    public synchronized void handleMessage(String str) {
        if (str.startsWith(">")) {
            LooperItem looperItem = this.mLastMessage;
            this.mLastMessage = new LooperItem(str);
        } else if (str.startsWith("<")) {
            if (this.mLastMessage != null) {
                this.mLastMessage.finish();
                if (this.mLastMessage.exceedsThreshold(this.mThreshold)) {
                    this.mHistory.addLast(this.mLastMessage);
                }
                this.mLastMessage = null;
                removeOldMessage();
            }
        }
    }

    private synchronized void removeOldMessage() {
        if (!this.mHistory.isEmpty()) {
            long uptimeMillis = SystemClock.uptimeMillis() - this.mDuration;
            while (!this.mHistory.getFirst().isAlive(uptimeMillis)) {
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
            for (int size = this.mHistory.size() - 1; size >= 0; size--) {
                this.mHistory.get(size).dumpToLogCat(String.valueOf(size));
            }
        }
    }
}
