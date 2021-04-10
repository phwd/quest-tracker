package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class DialogButton {
    private static final String DIALOG_BUTTON_ACTION_KEY = "action";
    private static final String DIALOG_BUTTON_AUTO_CLOSE_KEY = "autoClose";
    private static final String TAG = LoggingUtil.tag(DialogButton.class);
    private final String mButtonAction;
    private boolean mButtonAutoClose = true;

    public DialogButton(String str) {
        this.mButtonAction = str;
    }

    public void setDoesNotAutoClose() {
        this.mButtonAutoClose = false;
    }

    public JSONObject getDialogButtonConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("action", this.mButtonAction);
            if (!this.mButtonAutoClose) {
                jSONObject.put("autoClose", this.mButtonAutoClose);
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog base button configuration JSON.", e);
            throw e;
        }
    }
}
