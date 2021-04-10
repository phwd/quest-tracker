package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class DialogButton {
    public static final String DIALOG_BUTTON_ACTION_KEY = "action";
    public static final String DIALOG_BUTTON_AUTO_CLOSE_KEY = "autoClose";
    public static final String TAG = LoggingUtil.tag(DialogButton.class);
    public final String mButtonAction;
    public boolean mButtonAutoClose = true;

    public void setDoesNotAutoClose() {
        this.mButtonAutoClose = false;
    }

    public String getButtonAction() {
        return this.mButtonAction;
    }

    public JSONObject getDialogButtonConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", this.mButtonAction);
            boolean z = this.mButtonAutoClose;
            if (!z) {
                jSONObject.put("autoClose", z);
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog base button configuration JSON.", e);
            throw e;
        }
    }

    public DialogButton(String str) {
        this.mButtonAction = str;
    }
}
