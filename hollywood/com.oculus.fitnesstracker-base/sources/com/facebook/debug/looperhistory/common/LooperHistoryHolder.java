package com.facebook.debug.looperhistory.common;

import android.os.Looper;
import com.facebook.infer.annotation.Nullsafe;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class LooperHistoryHolder {
    private static final Map<Looper, ILooperHistory> sMaps = new HashMap();

    public static synchronized void dumpToLogCat(Looper looper) {
        synchronized (LooperHistoryHolder.class) {
            if (sMaps.get(looper) != null) {
            }
        }
    }
}
