package com.oculus.panelapp.assistant.config;

import com.oculus.assistant.service.api.panel.AssistantDialogContract;
import org.json.JSONException;
import org.json.JSONObject;

public class BroadcastConfig {
    private boolean mBroadcastMessages;
    private String mBroadcastPackage;

    public void applyJson(JSONObject jSONObject) throws JSONException {
        this.mBroadcastMessages = jSONObject.has(AssistantDialogContract.TARGET);
        this.mBroadcastPackage = jSONObject.optString(AssistantDialogContract.TARGET, null);
    }

    public String getResponseTarget() {
        return this.mBroadcastPackage;
    }

    public boolean useBroadcast() {
        return this.mBroadcastMessages;
    }
}
