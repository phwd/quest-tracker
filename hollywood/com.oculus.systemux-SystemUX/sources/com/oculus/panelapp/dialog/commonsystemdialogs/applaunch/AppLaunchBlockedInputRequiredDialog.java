package com.oculus.panelapp.dialog.commonsystemdialogs.applaunch;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.DialogPanelApp;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import java.util.Map;

public abstract class AppLaunchBlockedInputRequiredDialog extends CommonDialog {
    private static final String ACTION_PASSTHROUGH = "passthrough";
    private static final long REQUEST_3DOF_DELAY_MS = 500;
    private static final String RSAPI_GUARDIAN_CONFIG_3DOF_MODE = "10";
    private static final String TAG = LoggingUtil.tag(AppLaunchBlockedInputRequiredDialog.class);
    private boolean m3DofEnabled = false;
    private final Context mContext;
    private final DialogPanelApp mDialogPanelApp;
    private boolean mIsInputAcceptable = false;
    private long mLast3DofRequestTime;
    private boolean mPassthroughEnabled = false;
    private final int mResourceIdBody;
    private final int mResourceIdBodyWithPassthroughPrompt;
    private final int mResourceIdTitle;

    /* access modifiers changed from: protected */
    public abstract boolean getIsInputAcceptable(long j);

    public AppLaunchBlockedInputRequiredDialog(Context context, Map<String, String> map, DialogPanelApp dialogPanelApp, int i, int i2, int i3) {
        this.mContext = context;
        this.mDialogPanelApp = dialogPanelApp;
        this.mResourceIdTitle = i;
        this.mResourceIdBodyWithPassthroughPrompt = i2;
        this.mResourceIdBody = i3;
        setDialogConfiguration();
        request3DofMode();
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onAction(String str, String[] strArr) {
        if (ACTION_PASSTHROUGH.equals(str)) {
            setShouldUsePassthrough(true);
            this.mPassthroughEnabled = true;
            setDialogConfiguration();
        }
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public final boolean shouldSendActionToShell(String str, String[] strArr) {
        return !ACTION_PASSTHROUGH.equals(str);
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public final void onInputFlags(long j) {
        boolean isInputAcceptable = getIsInputAcceptable(j);
        if (this.mIsInputAcceptable != isInputAcceptable) {
            this.mIsInputAcceptable = isInputAcceptable;
            setDialogConfiguration();
        }
        if (System.currentTimeMillis() - this.mLast3DofRequestTime > 500) {
            request3DofMode();
        }
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public final void onIPC(String str, String str2) {
        if ("updateGuardianConfigValue".equals(str)) {
            String[] split = str2.split(" ");
            if (split.length == 2) {
                boolean z = false;
                if (RSAPI_GUARDIAN_CONFIG_3DOF_MODE.equals(split[0])) {
                    try {
                        if (((double) Float.valueOf(split[1]).floatValue()) > 0.5d) {
                            z = true;
                        }
                        if (this.m3DofEnabled != z) {
                            this.m3DofEnabled = z;
                            setDialogConfiguration();
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Failed to parse updateGuardianConfigValue: '" + str2 + "'", e);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean isPassthroughEnabled() {
        return this.mPassthroughEnabled;
    }

    private void setDialogConfiguration() {
        Resources resources = this.mContext.getResources();
        boolean z = !this.mPassthroughEnabled && !this.m3DofEnabled;
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CommonSystemDialog.APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED.getDialogId(), resources.getString(this.mResourceIdTitle), resources.getString(z ? this.mResourceIdBodyWithPassthroughPrompt : this.mResourceIdBody));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.app_launch_blocked_dialog_continue)).setDisabled(true ^ this.mIsInputAcceptable));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        if (z) {
            DialogButtonText dialogButtonText = new DialogButtonText(ACTION_PASSTHROUGH, resources.getString(R.string.app_launch_blocked_dialog_passthrough));
            dialogButtonText.setDoesNotAutoClose();
            dialogDefinitionCustom.setSecondaryButton(dialogButtonText);
            dialogDefinitionCustom.setTertiaryButton(new DialogButtonText("cancel", resources.getString(R.string.app_launch_blocked_dialog_cancel)));
        } else {
            dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.app_launch_blocked_dialog_cancel)));
        }
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }

    private void request3DofMode() {
        this.mDialogPanelApp.queueRawCommand("guardianController getGuardianConfigValue 10");
        this.mLast3DofRequestTime = System.currentTimeMillis();
    }
}
