package com.oculus.os;

import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;

public class AnalyticsEvent {
    private static final String EVENT_TAG = "utl_eventTag";
    public static final String IS_ARRAY_OF_BUNDLES = "utl_array_of_bundles";
    static final String LOGGING_PACKAGE = "utl_logging_package";
    private static final String OVERRIDE_EVENT_TIME = "utl_override_event_time";
    private static final String REALTIME_MS = "utl_realtime_ms";
    private static final String TAG = AnalyticsEvent.class.getSimpleName();
    private static final String UPTIME_MS = "utl_uptime_ms";
    private final PersistableBundle mExtras;
    @Deprecated
    private final PersistableBundle mInternalUseParams;
    private String mModuleName;
    private final String mName;

    private enum TagType {
        USL_ENABLED,
        LOGGED_THROUGH_REACT_NATIVE,
        NT_EVENT,
        LOGGED_THROUGH_XPLAT,
        HAS_DOWNLOADED_SAMPLING_POLICY,
        EVENT_IN_SAMPLING_CONFIG
    }

    public AnalyticsEvent(String name) {
        this((String) null, name);
    }

    public AnalyticsEvent(String moduleName, String name) {
        this(moduleName, name, null, null);
    }

    public AnalyticsEvent(String name, PersistableBundle extras) {
        this(null, name, extras, null);
    }

    public AnalyticsEvent(String moduleName, String name, PersistableBundle extras, PersistableBundle internalUseParams) {
        this.mModuleName = moduleName;
        this.mName = name;
        this.mExtras = new PersistableBundle();
        this.mInternalUseParams = new PersistableBundle();
        if (extras != null) {
            for (String key : extras.keySet()) {
                setExtra(key, extras.get(key));
            }
        }
        if (internalUseParams != null) {
            for (String key2 : internalUseParams.keySet()) {
                setInternalUseParam(key2, internalUseParams.get(key2));
            }
        }
        setExtra(UPTIME_MS, Long.valueOf(SystemClock.uptimeMillis()));
        setExtra(REALTIME_MS, Long.valueOf(SystemClock.elapsedRealtime()));
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

    private <T> AnalyticsEvent setContentValue(PersistableBundle bundle, String key, T value) {
        if (value instanceof String) {
            bundle.putString(key, value);
        } else if (value instanceof Integer) {
            bundle.putInt(key, value.intValue());
        } else if (value instanceof Double) {
            bundle.putDouble(key, value.doubleValue());
        } else if (value instanceof Long) {
            bundle.putLong(key, value.longValue());
        } else if (value instanceof Boolean) {
            bundle.putBoolean(key, value.booleanValue());
        } else if (value instanceof String[]) {
            bundle.putStringArray(key, (String[]) value);
        } else if (value instanceof int[]) {
            bundle.putIntArray(key, (int[]) value);
        } else if (value instanceof double[]) {
            bundle.putDoubleArray(key, (double[]) value);
        } else if (value instanceof long[]) {
            bundle.putLongArray(key, (long[]) value);
        } else if (value instanceof boolean[]) {
            bundle.putBooleanArray(key, (boolean[]) value);
        } else if (value instanceof PersistableBundle) {
            bundle.putPersistableBundle(key, value);
        } else {
            String str = TAG;
            Log.e(str, "could not set key=" + key + " value=" + String.valueOf(value));
        }
        return this;
    }

    public <T> AnalyticsEvent setExtra(String key, T value) {
        return setContentValue(this.mExtras, key, value);
    }

    public <T> AnalyticsEvent setInternalUseParam(String key, T value) {
        return setContentValue(this.mInternalUseParams, key, value);
    }

    public void setTime(long time) {
        setContentValue(this.mExtras, OVERRIDE_EVENT_TIME, Long.valueOf(time));
    }

    public void setUslTag() {
        setContentValue(this.mExtras, EVENT_TAG, Integer.valueOf(TagType.USL_ENABLED.ordinal()));
    }

    public void setReactNativeTag() {
        setContentValue(this.mExtras, EVENT_TAG, Integer.valueOf(TagType.LOGGED_THROUGH_REACT_NATIVE.ordinal()));
    }

    public void setNtTag() {
        setContentValue(this.mExtras, EVENT_TAG, Integer.valueOf(TagType.NT_EVENT.ordinal()));
    }

    public void setXplatTag() {
        setContentValue(this.mExtras, EVENT_TAG, Integer.valueOf(TagType.LOGGED_THROUGH_XPLAT.ordinal()));
    }

    public void setHasDownloadedSamplingConfigTag() {
        setContentValue(this.mExtras, EVENT_TAG, Integer.valueOf(TagType.HAS_DOWNLOADED_SAMPLING_POLICY.ordinal()));
    }

    public void setIsEventInSamplingConfigTag() {
        setContentValue(this.mExtras, EVENT_TAG, Integer.valueOf(TagType.EVENT_IN_SAMPLING_CONFIG.ordinal()));
    }
}
