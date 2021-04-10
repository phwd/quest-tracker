package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class DialogButtonText extends DialogButton {
    public static final String DIALOG_BUTTON_AUTO_CLOSE_KEY = "autoClose";
    public static final String DIALOG_BUTTON_DISABLED_KEY = "disabled";
    public static final String DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY = "disabledUntilBodyScrolledToBottom";
    public static final String DIALOG_BUTTON_TEXT_KEY = "text";
    public static final String TAG = LoggingUtil.tag(DialogButtonText.class);
    public boolean mButtonAutoClose = true;
    public boolean mButtonDisabled = false;
    public boolean mButtonDisabledUntilScrolledToBottom = false;
    public final String mButtonText;

    public DialogButtonText setDisabledUntilScrolledToBottom() {
        this.mButtonDisabledUntilScrolledToBottom = true;
        return this;
    }

    public boolean getAutoClose() {
        return this.mButtonAutoClose;
    }

    public boolean getDisabled() {
        return this.mButtonDisabled;
    }

    public boolean getDisabledUntilScrolledToBottom() {
        return this.mButtonDisabledUntilScrolledToBottom;
    }

    public String getText() {
        return this.mButtonText;
    }

    public DialogButtonText(String str, String str2) {
        super(str);
        this.mButtonText = str2;
    }

    @Override // com.oculus.systemdialog.DialogButton
    public JSONObject getDialogButtonConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject dialogButtonConfigurationIPCParcel = super.getDialogButtonConfigurationIPCParcel();
            dialogButtonConfigurationIPCParcel.put("text", this.mButtonText);
            if (this.mButtonDisabled) {
                dialogButtonConfigurationIPCParcel.put(DIALOG_BUTTON_DISABLED_KEY, true);
            }
            if (this.mButtonDisabledUntilScrolledToBottom) {
                dialogButtonConfigurationIPCParcel.put(DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY, true);
            }
            boolean z = this.mButtonAutoClose;
            if (!z) {
                dialogButtonConfigurationIPCParcel.put("autoClose", z);
            }
            return dialogButtonConfigurationIPCParcel;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog text button configuration JSON.", e);
            throw e;
        }
    }

    public DialogButtonText setAutoClose(boolean z) {
        this.mButtonAutoClose = z;
        return this;
    }

    public DialogButtonText setDisabled() {
        this.mButtonDisabled = true;
        return this;
    }

    public DialogButtonText setDisabled(boolean z) {
        this.mButtonDisabled = z;
        return this;
    }
}
