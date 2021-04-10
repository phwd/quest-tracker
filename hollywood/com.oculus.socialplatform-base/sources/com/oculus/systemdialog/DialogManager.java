package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public class DialogManager {
    public static final String TAG = LoggingUtil.tag(DialogManager.class);
    public DialogDefinitionBase mCurrentSystemDialog = null;
    public DialogState mSystemDialogState = DialogState.NoAction;

    public enum DialogState {
        NoAction,
        Show,
        Hide
    }

    public void hideDialog() {
        this.mCurrentSystemDialog = null;
        this.mSystemDialogState = DialogState.Hide;
    }

    public String getDialogIPC() {
        DialogState dialogState = this.mSystemDialogState;
        String str = null;
        if (dialogState == DialogState.Show) {
            str = String.format("dialog show %s", this.mCurrentSystemDialog.getDialogConfigurationIPCParcel());
        } else if (dialogState == DialogState.Hide) {
            this.mCurrentSystemDialog = null;
            str = "dialog hide";
        }
        this.mSystemDialogState = DialogState.NoAction;
        return str;
    }

    public String getDialogId() {
        DialogDefinitionBase dialogDefinitionBase = this.mCurrentSystemDialog;
        if (dialogDefinitionBase != null) {
            return dialogDefinitionBase.mDialogId;
        }
        return null;
    }

    public void showDialog(DialogDefinitionBase dialogDefinitionBase) {
        this.mCurrentSystemDialog = dialogDefinitionBase;
        this.mSystemDialogState = DialogState.Show;
    }

    public void handleSystemDialogResult(String str) {
        String str2;
        Object[] objArr;
        String str3;
        DialogResult fromJson = DialogResult.fromJson(str);
        if (fromJson == null) {
            str2 = TAG;
            objArr = new Object[]{str};
            str3 = "null dialog result cannot be processed, invalid dialog result string: %s. Closing system dialog.";
        } else {
            DialogDefinitionBase dialogDefinitionBase = this.mCurrentSystemDialog;
            if (dialogDefinitionBase == null) {
                str2 = TAG;
                objArr = new Object[]{fromJson.toString()};
                str3 = "null dialog cannot handle dialog result: %s. Closing system dialog.";
            } else {
                if (fromJson.mDialogAutoClose) {
                    hideDialog();
                }
                if (!dialogDefinitionBase.handleDialogResult(fromJson)) {
                    fromJson.toString();
                    hideDialog();
                }
                return;
            }
        }
        Log.e(str2, String.format(str3, objArr));
        hideDialog();
    }
}
