package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public final class LaunchCheckRequires6dofDialogDefinition extends CustomSystemDialogDefinition {
    public LaunchCheckRequires6dofDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.LAUNCH_CHECK_REQUIRES_6DOF.path(), context.getResources().getString(R.string.launch_check_requires_6dof_dialog_title), context.getResources().getString(R.string.launch_check_requires_6dof_dialog_description)).setPrimaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.launch_check_dialog_generic_cancel_button))).setSecondaryButton(new DialogButtonText("continue", context.getResources().getString(R.string.launch_check_requires_6dof_dialog_tracking_enable_button))).setControllerBackButton(new DialogButton("close")));
    }
}
