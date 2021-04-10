package com.oculus.os;

import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;

public class AnalyticsEvent {
    private static final String TAG = "AnalyticsEvent";
    private final PersistableBundle mExtras;
    @Deprecated
    private final PersistableBundle mInternalUseParams;
    private String mModuleName;
    private final String mName;

    public AnalyticsEvent(String str) {
        this(null, str);
    }

    public AnalyticsEvent(String str, String str2) {
        this(str, str2, null, null);
    }

    public AnalyticsEvent(String str, String str2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2) {
        this.mModuleName = str;
        this.mName = str2;
        this.mExtras = new PersistableBundle();
        this.mInternalUseParams = new PersistableBundle();
        if (persistableBundle != null) {
            for (String str3 : persistableBundle.keySet()) {
                setExtra(str3, persistableBundle.get(str3));
            }
        }
        if (persistableBundle2 != null) {
            for (String str4 : persistableBundle2.keySet()) {
                setInternalUseParam(str4, persistableBundle2.get(str4));
            }
        }
        setExtra("utl_uptime_ms", Long.valueOf(SystemClock.uptimeMillis()));
        setExtra("utl_realtime_ms", Long.valueOf(SystemClock.elapsedRealtime()));
    }

    public String getModuleName() {
        return this.mModuleName;
    }

    public String getName() {
        return this.mName;
    }

    public PersistableBundle getExtras() {
        return this.mExtras;
    }

    public PersistableBundle getInternalUseParams() {
        return this.mInternalUseParams;
    }

    private <T> AnalyticsEvent setContentValue(PersistableBundle persistableBundle, String str, T t) {
        if (t instanceof String) {
            persistableBundle.putString(str, t);
        } else if (t instanceof Integer) {
            persistableBundle.putInt(str, t.intValue());
        } else if (t instanceof Double) {
            persistableBundle.putDouble(str, t.doubleValue());
        } else if (t instanceof Long) {
            persistableBundle.putLong(str, t.longValue());
        } else if (t instanceof Boolean) {
            persistableBundle.putBoolean(str, t.booleanValue());
        } else if (t instanceof String[]) {
            persistableBundle.putStringArray(str, (String[]) t);
        } else if (t instanceof int[]) {
            persistableBundle.putIntArray(str, (int[]) t);
        } else if (t instanceof double[]) {
            persistableBundle.putDoubleArray(str, (double[]) t);
        } else if (t instanceof long[]) {
            persistableBundle.putLongArray(str, (long[]) t);
        } else if (t instanceof boolean[]) {
            persistableBundle.putBooleanArray(str, (boolean[]) t);
        } else if (t instanceof PersistableBundle) {
            persistableBundle.putPersistableBundle(str, t);
        } else {
            String str2 = TAG;
            Log.e(str2, "could not set key=" + str + " value=" + String.valueOf(t));
        }
        return this;
    }

    public <T> AnalyticsEvent setExtra(String str, T t) {
        setContentValue(this.mExtras, str, t);
        return this;
    }

    public <T> AnalyticsEvent setInternalUseParam(String str, T t) {
        setContentValue(this.mInternalUseParams, str, t);
        return this;
    }
}
