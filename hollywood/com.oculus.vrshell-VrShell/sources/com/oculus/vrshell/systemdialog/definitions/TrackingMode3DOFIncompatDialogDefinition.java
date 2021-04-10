package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public final class TrackingMode3DOFIncompatDialogDefinition extends CustomSystemDialogDefinition {
    public TrackingMode3DOFIncompatDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.TRACKING_MODE_3DOF_INCOMPAT_DIALOG.path(), context.getResources().getString(R.string.tracking_mode_3dof_incompat_title), context.getResources().getString(R.string.tracking_mode_3dof_incompat_desc)).setPrimaryButton(new DialogButtonText(SystemDialogActions.TRACKING_MODE_3DOF_INCOMPAT_DIALOG_CONTINUE_WITH_3DOF_ACTION, context.getResources().getString(R.string.tracking_mode_3dof_incompat_ok))).setSecondaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.guardian_adjust_dialog_cancel))).setControllerBackButton(new DialogButton("cancel")));
    }
}
