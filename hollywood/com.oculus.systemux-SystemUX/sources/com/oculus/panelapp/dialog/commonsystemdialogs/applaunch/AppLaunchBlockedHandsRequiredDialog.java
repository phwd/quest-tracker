package com.oculus.panelapp.dialog.commonsystemdialogs.applaunch;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.dialog.DialogPanelApp;
import com.oculus.panelapp.dialog.R;
import com.oculus.vrshell.panels.PanelFlags;
import java.util.Map;

public final class AppLaunchBlockedHandsRequiredDialog extends AppLaunchBlockedInputRequiredDialog {
    private static final String TAG = LoggingUtil.tag(AppLaunchBlockedHandsRequiredDialog.class);
    private final SettingsManager mSettingsManager = new SettingsManager();
    private final boolean mWasAutotransitionEnabled = this.mSettingsManager.getBoolean(SettingsManager.AUTOTRANSITION_HANDS_CONTROLLERS, false);

    public AppLaunchBlockedHandsRequiredDialog(Context context, Map<String, String> map, DialogPanelApp dialogPanelApp) {
        super(context, map, dialogPanelApp, R.string.app_launch_blocked_hands_required_dialog_title, R.string.app_launch_blocked_hands_required_dialog_description_with_passthrough_option, R.string.app_launch_blocked_hands_required_dialog_description);
        Log.d(TAG, "AppLaunchBlockedHandsRequiredDialog()");
        if (!this.mWasAutotransitionEnabled) {
            this.mSettingsManager.setBoolean(SettingsManager.AUTOTRANSITION_HANDS_CONTROLLERS, true);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedInputRequiredDialog
    public boolean getIsInputAcceptable(long j) {
        return PanelFlags.HAND_ACTIVE.isEnabled(j);
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedInputRequiredDialog, com.oculus.panelapp.dialog.CommonDialog
    public void onAction(String str, String[] strArr) {
        super.onAction(str, strArr);
        if (shouldSendActionToShell(str, strArr) && !this.mWasAutotransitionEnabled) {
            this.mSettingsManager.setBoolean(SettingsManager.AUTOTRANSITION_HANDS_CONTROLLERS, false);
        }
    }
}
