package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class DialogButtonText extends DialogButton {
    private static final String DIALOG_BUTTON_DISABLED_KEY = "disabled";
    private static final String DIALOG_BUTTON_DISABLED_UNTIL_SCROLLED_TO_BOTTOM_KEY = "disabledUntilBodyScrolledToBottom";
    private static final String DIALOG_BUTTON_TEXT_KEY = "text";
    private static final String TAG = LoggingUtil.tag(DialogButtonText.class);
    private boolean mButtonDisabled = false;
    private boolean mButtonDisabledUntilScrolledToBottom = false;
    private final String mButtonText;

    public DialogButtonText(String str, String str2) {
        super(str);
        this.mButtonText = str2;
    }

    public DialogButtonText setDisabled(boolean z) {
        this.mButtonDisabled = z;
        return this;
    }

    public DialogButtonText setDisabled() {
        return setDisabled(true);
    }

    public DialogButtonText setDisabledUntilScrolledToBottom() {
        this.mButtonDisabledUntilScrolledToBottom = true;
        return this;
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
            return dialogButtonConfigurationIPCParcel;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog text button configuration JSON.", e);
            throw e;
        }
    }
}
