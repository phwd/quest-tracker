package com.oculus.panelapp.dialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogDefinitionCustom;
import java.util.Map;
import org.json.JSONException;

public class CommonDialog {
    private static final String TAG = LoggingUtil.tag(CommonDialog.class);
    private String mPendingDialogConfiguration;
    private String mPendingSyntheticButtonClick;
    private boolean mShouldUsePassthrough;

    /* access modifiers changed from: protected */
    public void onAction(String str, String[] strArr) {
    }

    public void onClose() {
    }

    public void onIPC(String str, String str2) {
    }

    public void onInputFlags(long j) {
    }

    /* access modifiers changed from: protected */
    public boolean shouldSendActionToShell(String str, String[] strArr) {
        return true;
    }

    /* access modifiers changed from: protected */
    public final synchronized void setPendingDialogConfiguration(DialogDefinitionCustom dialogDefinitionCustom) {
        this.mPendingDialogConfiguration = dialogDefinitionCustom.getDialogConfigurationIPCParcel();
    }

    public final synchronized String getPendingDialogConfigurationIPCParcel() {
        if (this.mPendingDialogConfiguration == null) {
            return "";
        }
        String str = this.mPendingDialogConfiguration;
        this.mPendingDialogConfiguration = null;
        return str;
    }

    /* access modifiers changed from: protected */
    public final synchronized void setPendingSyntheticButtonClick(DialogButton dialogButton) {
        try {
            this.mPendingSyntheticButtonClick = dialogButton.getDialogButtonConfigurationIPCParcel().toString();
        } catch (JSONException e) {
            Log.e(TAG, "Unable to set pending synthetic button click.", e);
            this.mPendingSyntheticButtonClick = "";
        }
        return;
    }

    public final synchronized String getPendingSyntheticButtonClickIPCParcel() {
        if (this.mPendingSyntheticButtonClick == null) {
            return "";
        }
        String str = this.mPendingSyntheticButtonClick;
        this.mPendingSyntheticButtonClick = null;
        return str;
    }

    /* access modifiers changed from: protected */
    public final synchronized void setShouldUsePassthrough(boolean z) {
        this.mShouldUsePassthrough = z;
    }

    public final synchronized boolean getShouldUsePassthrough() {
        return this.mShouldUsePassthrough;
    }

    public final boolean handleAndGetShouldSendActionToShell(String str, String[] strArr) {
        onAction(str, strArr);
        return shouldSendActionToShell(str, strArr);
    }

    /* access modifiers changed from: protected */
    public final String getStringParameterOrDefault(Map<String, String> map, String str, String str2) {
        String str3 = map.get(str);
        return str3 == null ? str2 : str3;
    }

    /* access modifiers changed from: protected */
    public final boolean getBooleanParameterOrDefault(Map<String, String> map, String str, boolean z) {
        String str2 = map.get(str);
        if (str2 == null) {
            return z;
        }
        return Boolean.valueOf(str2).booleanValue();
    }
}
