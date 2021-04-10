package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public final class TrackingOffDialogDefinition extends CustomSystemDialogDefinition {
    public TrackingOffDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.TRACKING_OFF_DIALOG.path(), context.getResources().getString(R.string.tracking_off_title), context.getResources().getString(R.string.tracking_off_desc)).setPrimaryButton(new DialogButtonText("turn_on_tracking", context.getResources().getString(R.string.tracking_off_accept_button))).setSecondaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.tracking_off_cancel_button))).setControllerBackButton(new DialogButton("cancel")));
    }
}
