package com.oculus.video.analytics;

import android.util.Log;
import com.facebook.mobileconfigservice.client_base.MobileConfigClientDataLogger;
import com.oculus.auth.service.contract.ServiceContract;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoPlayerAnalyticsLogEvent extends JSONObject {
    private static final String TAG = "VideoPlayerAnalyticsLogEvent";
    private final String eventName;

    public VideoPlayerAnalyticsLogEvent(String str) throws JSONException {
        this.eventName = str;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ServiceContract.EXTRA_NAME, this.eventName);
            jSONObject.put(MobileConfigClientDataLogger.EXTRA_FIELD, this);
        } catch (JSONException unused) {
            Log.w(TAG, "Unable to construct the analyticsLogEvent");
        }
        return jSONObject.toString();
    }
}
