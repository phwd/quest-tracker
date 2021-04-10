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
            ILooperHistory history = sMaps.get(looper);
            if (history != null) {
                history.dumpToLogCat();
            }
        }
    }

    public static synchronized void put(Looper looper, ILooperHistory history) {
        synchronized (LooperHistoryHolder.class) {
            sMaps.put(looper, history);
        }
    }
}
