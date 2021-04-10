package com.facebook.debug.looperhistory.common;

import android.os.Looper;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class LooperHistoryHolder {
    private static final Map<Looper, ILooperHistory> sMaps = new HashMap();

    public static synchronized void dumpToLogCat(Looper looper) {
        synchronized (LooperHistoryHolder.class) {
            ILooperHistory iLooperHistory = sMaps.get(looper);
            if (iLooperHistory != null) {
                iLooperHistory.dumpToLogCat();
            }
        }
    }

    public static synchronized void put(Looper looper, ILooperHistory iLooperHistory) {
        synchronized (LooperHistoryHolder.class) {
            sMaps.put(looper, iLooperHistory);
        }
    }
}
