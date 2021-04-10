package com.oculus.updater.core.logging;

import android.os.Build;
import com.facebook.acra.constants.ErrorReportingConstants;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.debug.log.BLog;
import com.oculus.common.serial.BuildSerialUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class OSUpdateEvent {
    private static final String TAG = "OSUpdateEvent";
    private final JSONObject mEventData = new JSONObject();
    private final String mEventName;
    private final boolean mIsLowLatency;

    public OSUpdateEvent(String str, boolean z) {
        this.mEventName = str;
        this.mIsLowLatency = z;
        setKey("device_id", BuildSerialUtil.getSerial());
        setKey("build_flavor", SystemPropertiesInternal.get("ro.build.flavor", "unknown"));
        setKey(ErrorReportingConstants.BUILD_TYPE, Build.TYPE);
        setKey("build_version", Build.VERSION.INCREMENTAL);
        setKey("timestamp_msec", Long.valueOf(System.currentTimeMillis()));
    }

    public boolean getIsLowLatency() {
        return this.mIsLowLatency;
    }

    public String getName() {
        return this.mEventName;
    }

    public JSONObject getData() {
        return this.mEventData;
    }

    public synchronized <T> void setKey(String str, T t) {
        try {
            this.mEventData.put(str, t);
        } catch (JSONException e) {
            BLog.e(TAG, "could not add %s to the event %s", str, this.mEventName, e);
        }
        return;
    }
}
