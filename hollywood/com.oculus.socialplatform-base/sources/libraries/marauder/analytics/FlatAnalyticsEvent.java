package libraries.marauder.analytics;

import libraries.debug.log.BLog;
import org.json.JSONException;
import org.json.JSONObject;

public class FlatAnalyticsEvent extends AnalyticsEventBase {
    public FlatAnalyticsEvent addPayload(String str, String str2) {
        try {
            this.mPayload.put(str, str2);
            return this;
        } catch (JSONException e) {
            BLog.e(AnalyticsEventBase.TAG, "", e);
            return this;
        }
    }

    @Override // libraries.marauder.analytics.IAnalyticsEvent
    public String toString() {
        try {
            this.mPayload.put(AnalyticsEventBase.EXTRAS_KEY, this.mExtras);
            return this.mPayload.toString();
        } catch (JSONException e) {
            BLog.e(AnalyticsEventBase.TAG, "FATAL", e);
            return "{}";
        }
    }

    public FlatAnalyticsEvent(String str) {
        try {
            this.mPayload.put("name", str);
            this.mPayload.put(AnalyticsEventBase.TIME_KEY, AnalyticsUtil.formatServerTime(System.currentTimeMillis()));
        } catch (JSONException e) {
            BLog.e(AnalyticsEventBase.TAG, "", e);
        }
    }

    public FlatAnalyticsEvent(String str, String str2) {
        this(str);
        try {
            this.mExtras = new JSONObject(str2);
        } catch (JSONException e) {
            BLog.e(AnalyticsEventBase.TAG, "", e);
        }
    }
}
