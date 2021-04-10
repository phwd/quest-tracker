package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.systemdialog.DialogIcon;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogInformationBox {
    public static final String DIALOG_INFORMATION_BOX_ICON_KEY = "icon";
    public static final String DIALOG_INFORMATION_BOX_TEXT_KEY = "text";
    public static final String TAG = LoggingUtil.tag(DialogInformationBox.class);
    public DialogIcon.InformationBox mInformationBoxIcon;
    public final String mInformationBoxText;

    public JSONObject getDialogInformationBoxConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("text", this.mInformationBoxText);
            jSONObject.put("icon", this.mInformationBoxIcon.getIPCString());
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get information box configuration IPC parcel.", e);
            throw e;
        }
    }

    public String getText() {
        return this.mInformationBoxText;
    }

    public DialogInformationBox(String str) {
        this(str, DialogIcon.InformationBox.INFO);
    }

    public DialogInformationBox(String str, DialogIcon.InformationBox informationBox) {
        this.mInformationBoxText = str;
        this.mInformationBoxIcon = informationBox;
    }
}
