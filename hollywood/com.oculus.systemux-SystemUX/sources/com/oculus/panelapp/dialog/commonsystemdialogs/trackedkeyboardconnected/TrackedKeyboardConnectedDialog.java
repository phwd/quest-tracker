package com.oculus.panelapp.dialog.commonsystemdialogs.trackedkeyboardconnected;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;

public final class TrackedKeyboardConnectedDialog extends CommonDialog {
    private static final String TAG = LoggingUtil.tag(TrackedKeyboardConnectedDialog.class);

    public TrackedKeyboardConnectedDialog(Context context) {
        Log.d(TAG, "Creating dialog");
        setDialogConfiguration(context);
        Log.d(TAG, "Created dialog");
    }

    private void setDialogConfiguration(Context context) {
        Resources resources = context.getResources();
        setPendingDialogConfiguration(new DialogDefinitionCustom(CommonSystemDialog.TRACKED_KEYBOARD_CONNECTED.getDialogId(), resources.getString(R.string.tracked_keyboard_connected_title), resources.getString(R.string.tracked_keyboard_connected_body)).setPrimaryButton(new DialogButtonText("close", resources.getString(R.string.tracked_keyboard_connected_dismiss))).setControllerBackButton(new DialogButton("close")));
    }
}
