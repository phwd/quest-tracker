package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.systemdialog.DialogIcon;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogButtonIcon extends DialogButton {
    private static final String DIALOG_BUTTON_ICON_KEY = "icon";
    private static final String TAG = LoggingUtil.tag(DialogButtonIcon.class);
    private final DialogIcon.IconButton mButtonIcon;

    public DialogButtonIcon(String str, DialogIcon.IconButton iconButton) {
        super(str);
        this.mButtonIcon = iconButton;
    }

    @Override // com.oculus.systemdialog.DialogButton
    public JSONObject getDialogButtonConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject dialogButtonConfigurationIPCParcel = super.getDialogButtonConfigurationIPCParcel();
            dialogButtonConfigurationIPCParcel.put("icon", this.mButtonIcon.getIPCString());
            return dialogButtonConfigurationIPCParcel;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog icon button configuration JSON.", e);
            throw e;
        }
    }
}
