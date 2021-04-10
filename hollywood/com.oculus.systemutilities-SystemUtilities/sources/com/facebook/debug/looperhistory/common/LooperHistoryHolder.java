package com.facebook.debug.looperhistory.common;

import android.os.Looper;
import java.util.HashMap;
import java.util.Map;

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
}
