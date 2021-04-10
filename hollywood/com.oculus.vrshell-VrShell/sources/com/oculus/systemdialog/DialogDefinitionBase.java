package com.oculus.systemdialog;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;

public abstract class DialogDefinitionBase {
    public static final String DIALOG_ID_KEY = "dialogId";
    public static final String DIALOG_TYPE_COMMON_VALUE = "common";
    public static final String DIALOG_TYPE_CUSTOM_VALUE = "custom";
    public static final String DIALOG_TYPE_KEY = "type";
    private static final String TAG = LoggingUtil.tag(DialogDefinitionBase.class);
    protected final String mDialogId;
    private DialogResultHandler mDialogResultHandler;

    public abstract String getDialogConfigurationIPCParcel();

    public DialogDefinitionBase(String str) {
        this.mDialogId = str;
    }

    public void setDialogResultHandler(DialogResultHandler dialogResultHandler) {
        this.mDialogResultHandler = dialogResultHandler;
    }

    public boolean handleDialogResult(@Nullable DialogResult dialogResult) {
        if (dialogResult == null) {
            Log.e(TAG, "Unable to handle null dialog result.");
            return false;
        }
        String dialogId = dialogResult.getDialogId();
        if (!this.mDialogId.equals(dialogId)) {
            Log.e(TAG, String.format("Expected dialog ID \"%s\" but found \"%s\" instead. Unable to handle dialog result: %s.", this.mDialogId, dialogId, dialogResult.toString()));
            return false;
        }
        DialogResultHandler dialogResultHandler = this.mDialogResultHandler;
        if (dialogResultHandler == null) {
            Log.i(TAG, String.format("Dialog result handler not specified for dialog result: %s.", dialogResult.toString()));
            return false;
        } else if (dialogResultHandler.handleDialogResult(dialogResult)) {
            return true;
        } else {
            Log.e(TAG, String.format("The current dialog result handler does not handle dialog result: %s.", dialogResult.toString()));
            return false;
        }
    }
}
