package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public final class AppModeIncompatibleDialogDefinition extends CustomSystemDialogDefinition {
    public AppModeIncompatibleDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.APP_MODE_INCOMPATIBLE.path(), context.getResources().getString(R.string.app_mode_incompat_dialog_title), context.getResources().getString(R.string.app_mode_incompat_dialog_description)).setPrimaryButton(new DialogButtonText("turn_on_tracking", context.getResources().getString(R.string.app_mode_incompat_tracking_button))).setSecondaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.app_mode_incompat_cancel_button))).setControllerBackButton(new DialogButton("close")));
    }
}
