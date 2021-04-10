package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.assistant;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class AssistantActivityLogItem {
    private static final String TAG = LoggingUtil.tag(AssistantActivityLogItem.class);
    private final boolean mIsFakeWake;
    private final String mLogID;
    private final String mTimestamp;
    private final String mUtterance;
    private final String mUtteranceUrl;

    private AssistantActivityLogItem(String str, String str2, String str3, String str4, boolean z) {
        this.mLogID = str;
        this.mTimestamp = str2;
        this.mUtterance = str3;
        this.mUtteranceUrl = str4;
        this.mIsFakeWake = z;
    }

    public String getLogID() {
        return this.mLogID;
    }

    public String getUtterance() {
        return this.mUtterance;
    }

    public String getUtteranceUrl() {
        return this.mUtteranceUrl;
    }

    public String getTimestamp() {
        return this.mTimestamp;
    }

    public boolean isFakeWake() {
        return this.mIsFakeWake;
    }

    public static AssistantActivityLogItem fromJSON(JSONObject jSONObject) {
        try {
            return new AssistantActivityLogItem(jSONObject.getString("log_id"), jSONObject.getString("timestamp"), jSONObject.getString("utterance"), jSONObject.getString("utterance_url"), jSONObject.getBoolean("is_false_wake"));
        } catch (JSONException e) {
            Log.e(TAG, "Error parsing json", e);
            return null;
        }
    }
}
