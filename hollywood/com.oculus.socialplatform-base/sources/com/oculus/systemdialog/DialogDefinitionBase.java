package com.oculus.systemdialog;

import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;

public abstract class DialogDefinitionBase {
    public static final String DIALOG_ID_KEY = "dialogId";
    public static final String DIALOG_TYPE_COMMON_VALUE = "common";
    public static final String DIALOG_TYPE_CUSTOM_VALUE = "custom";
    public static final String DIALOG_TYPE_KEY = "type";
    public static final String TAG = LoggingUtil.tag(DialogDefinitionBase.class);
    public final String mDialogId;
    public DialogResultHandler mDialogResultHandler;

    public abstract String getDialogConfigurationIPCParcel();

    public boolean handleDialogResult(@Nullable DialogResult dialogResult) {
        String str;
        Object[] objArr;
        String str2;
        if (dialogResult == null) {
            Log.e(TAG, "Unable to handle null dialog result.");
            return false;
        }
        String str3 = dialogResult.mDialogId;
        String str4 = this.mDialogId;
        if (!str4.equals(str3)) {
            str = TAG;
            objArr = new Object[]{str4, str3, dialogResult.toString()};
            str2 = "Expected dialog ID \"%s\" but found \"%s\" instead. Unable to handle dialog result: %s.";
        } else {
            DialogResultHandler dialogResultHandler = this.mDialogResultHandler;
            if (dialogResultHandler == null) {
                dialogResult.toString();
                return false;
            } else if (dialogResultHandler.handleDialogResult(dialogResult)) {
                return true;
            } else {
                str = TAG;
                objArr = new Object[]{dialogResult.toString()};
                str2 = "The current dialog result handler does not handle dialog result: %s.";
            }
        }
        Log.e(str, String.format(str2, objArr));
        return false;
    }

    public String getDialogId() {
        return this.mDialogId;
    }

    public DialogDefinitionBase(String str) {
        this.mDialogId = str;
    }

    public void setDialogResultHandler(DialogResultHandler dialogResultHandler) {
        this.mDialogResultHandler = dialogResultHandler;
    }
}
