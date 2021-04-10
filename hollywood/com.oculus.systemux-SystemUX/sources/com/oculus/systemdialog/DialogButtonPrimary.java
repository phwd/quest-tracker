package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogButtonPrimary extends DialogButtonText {
    private static final String DIALOG_BUTTON_PRIMARY_STYLE_KEY = "style";
    private static final String TAG = LoggingUtil.tag(DialogButtonPrimary.class);
    private final DialogPrimaryButtonStyle mStyle;

    public DialogButtonPrimary(String str, String str2) {
        this(str, str2, DialogPrimaryButtonStyle.PRIMARY);
    }

    public DialogButtonPrimary(String str, String str2, DialogPrimaryButtonStyle dialogPrimaryButtonStyle) {
        super(str, str2);
        this.mStyle = dialogPrimaryButtonStyle;
    }

    public DialogPrimaryButtonStyle getStyle() {
        return this.mStyle;
    }

    @Override // com.oculus.systemdialog.DialogButton, com.oculus.systemdialog.DialogButtonText
    public JSONObject getDialogButtonConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject dialogButtonConfigurationIPCParcel = super.getDialogButtonConfigurationIPCParcel();
            if (!this.mStyle.equals(DialogPrimaryButtonStyle.PRIMARY)) {
                dialogButtonConfigurationIPCParcel.put("style", this.mStyle.getIPCString());
            }
            return dialogButtonConfigurationIPCParcel;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog primary button configuration JSON.", e);
            throw e;
        }
    }
}
