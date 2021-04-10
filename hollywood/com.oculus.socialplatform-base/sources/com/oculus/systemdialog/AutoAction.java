package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class AutoAction {
    public static final String DIALOG_AUTO_ACTION_ACTION_KEY = "action";
    public static final String DIALOG_AUTO_ACTION_DELAY_KEY = "delay";
    public static final String TAG = LoggingUtil.tag(AutoAction.class);
    public final String mAction;
    public final int mDelayMilliseconds;

    public String getAction() {
        return this.mAction;
    }

    public JSONObject getAutoActionConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", this.mAction);
            jSONObject.put(DIALOG_AUTO_ACTION_DELAY_KEY, this.mDelayMilliseconds);
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog auto-action configuration JSON.", e);
            throw e;
        }
    }

    public int getDelayMilliseconds() {
        return this.mDelayMilliseconds;
    }

    public AutoAction(String str, int i) {
        this.mAction = str;
        this.mDelayMilliseconds = i;
    }
}
