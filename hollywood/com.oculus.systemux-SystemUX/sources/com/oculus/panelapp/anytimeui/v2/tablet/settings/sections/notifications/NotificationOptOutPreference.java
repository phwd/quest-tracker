package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.notifications;

import android.util.Log;
import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationOptOutPreference {
    private static final String TAG = LoggingUtil.tag(NotificationOptOutPreference.class);
    private final String mDescription;
    private final String mKey;
    private final String mName;
    private boolean mOptOutValue;

    public NotificationOptOutPreference(String str, boolean z, String str2, String str3) {
        this.mKey = str;
        this.mOptOutValue = z;
        this.mName = str2;
        this.mDescription = str3;
    }

    public String getKey() {
        return this.mKey;
    }

    public boolean getOptOutValue() {
        return this.mOptOutValue;
    }

    public void setOptOutValue(boolean z) {
        this.mOptOutValue = z;
    }

    public String getName() {
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public static NotificationOptOutPreference fromJSON(JSONObject jSONObject) {
        try {
            return new NotificationOptOutPreference(jSONObject.getString("key"), jSONObject.getBoolean("opt_out_value"), jSONObject.getString("name"), jSONObject.getString(AssistantDialogContract.Dialog.DESCRIPTION));
        } catch (JSONException e) {
            Log.d(TAG, "Error parsing json", e);
            return null;
        }
    }
}
