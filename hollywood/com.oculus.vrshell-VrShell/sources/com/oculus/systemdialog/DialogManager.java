package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public class DialogManager {
    private static final String TAG = LoggingUtil.tag(DialogManager.class);
    private DialogDefinitionBase mCurrentSystemDialog = null;
    private DialogState mSystemDialogState = DialogState.NoAction;

    /* access modifiers changed from: private */
    public enum DialogState {
        NoAction,
        Show,
        Hide
    }

    public void showDialog(DialogDefinitionBase dialogDefinitionBase) {
        this.mCurrentSystemDialog = dialogDefinitionBase;
        this.mSystemDialogState = DialogState.Show;
    }

    public void hideDialog() {
        this.mCurrentSystemDialog = null;
        this.mSystemDialogState = DialogState.Hide;
    }

    public void handleSystemDialogResult(String str) {
        String str2 = TAG;
        Log.i(str2, "Received system dialog result: " + str);
        DialogResult fromJson = DialogResult.fromJson(str);
        if (fromJson == null) {
            Log.e(TAG, String.format("null dialog result cannot be processed, invalid dialog result string: %s. Closing system dialog.", str));
            hideDialog();
            return;
        }
        DialogDefinitionBase dialogDefinitionBase = this.mCurrentSystemDialog;
        if (dialogDefinitionBase == null) {
            Log.e(TAG, String.format("null dialog cannot handle dialog result: %s. Closing system dialog.", fromJson.toString()));
            hideDialog();
            return;
        }
        if (fromJson.getDialogAutoClose()) {
            Log.i(TAG, "The current dialog was auto-closed by VrShell. Closing system dialog.");
            hideDialog();
        }
        if (!dialogDefinitionBase.handleDialogResult(fromJson)) {
            Log.i(TAG, String.format("The current dialog does not handle dialog result: %s. Closing system dialog.", fromJson.toString()));
            hideDialog();
        }
    }

    public String getDialogIPC() {
        String str = null;
        if (this.mSystemDialogState == DialogState.Show) {
            str = String.format("dialog show %s", this.mCurrentSystemDialog.getDialogConfigurationIPCParcel());
        } else if (this.mSystemDialogState == DialogState.Hide) {
            this.mCurrentSystemDialog = null;
            str = "dialog hide";
        }
        this.mSystemDialogState = DialogState.NoAction;
        return str;
    }
}
