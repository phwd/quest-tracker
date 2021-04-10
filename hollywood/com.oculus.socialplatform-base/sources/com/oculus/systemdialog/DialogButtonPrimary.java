package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogButtonPrimary extends DialogButtonText {
    public static final String DIALOG_BUTTON_PRIMARY_STYLE_KEY = "style";
    public static final String TAG = LoggingUtil.tag(DialogButtonPrimary.class);
    public final DialogPrimaryButtonStyle mStyle;

    public DialogPrimaryButtonStyle getStyle() {
        return this.mStyle;
    }

    @Override // com.oculus.systemdialog.DialogButton, com.oculus.systemdialog.DialogButtonText
    public JSONObject getDialogButtonConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject dialogButtonConfigurationIPCParcel = super.getDialogButtonConfigurationIPCParcel();
            DialogPrimaryButtonStyle dialogPrimaryButtonStyle = this.mStyle;
            if (!dialogPrimaryButtonStyle.equals(DialogPrimaryButtonStyle.PRIMARY)) {
                dialogButtonConfigurationIPCParcel.put(DIALOG_BUTTON_PRIMARY_STYLE_KEY, dialogPrimaryButtonStyle.getIPCString());
            }
            return dialogButtonConfigurationIPCParcel;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog primary button configuration JSON.", e);
            throw e;
        }
    }

    public DialogButtonPrimary(String str, String str2) {
        this(str, str2, DialogPrimaryButtonStyle.PRIMARY);
    }

    public DialogButtonPrimary(String str, String str2, DialogPrimaryButtonStyle dialogPrimaryButtonStyle) {
        super(str, str2);
        this.mStyle = dialogPrimaryButtonStyle;
    }
}
