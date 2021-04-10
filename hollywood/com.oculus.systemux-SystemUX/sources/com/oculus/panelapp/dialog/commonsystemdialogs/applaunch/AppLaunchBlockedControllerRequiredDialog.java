package com.oculus.panelapp.dialog.commonsystemdialogs.applaunch;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.DialogPanelApp;
import com.oculus.panelapp.dialog.R;
import com.oculus.vrshell.panels.PanelFlags;
import java.util.Map;

public final class AppLaunchBlockedControllerRequiredDialog extends AppLaunchBlockedInputRequiredDialog {
    private static final String TAG = LoggingUtil.tag(AppLaunchBlockedControllerRequiredDialog.class);

    public AppLaunchBlockedControllerRequiredDialog(Context context, Map<String, String> map, DialogPanelApp dialogPanelApp) {
        super(context, map, dialogPanelApp, R.string.app_launch_blocked_controllers_required_dialog_title, R.string.app_launch_blocked_controllers_required_dialog_description_with_passthrough_option, R.string.app_launch_blocked_controllers_required_dialog_description);
        Log.d(TAG, "AppLaunchBlockedControllerRequiredDialog()");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.applaunch.AppLaunchBlockedInputRequiredDialog
    public boolean getIsInputAcceptable(long j) {
        return !PanelFlags.HAND_ACTIVE.isEnabled(j);
    }
}
