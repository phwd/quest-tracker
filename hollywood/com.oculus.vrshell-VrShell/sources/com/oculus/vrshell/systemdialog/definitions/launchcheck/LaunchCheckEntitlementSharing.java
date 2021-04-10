package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public final class LaunchCheckEntitlementSharing extends CustomSystemDialogDefinition {
    public LaunchCheckEntitlementSharing(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.LAUNCH_CHECK_ENTITLEMENT_SHARING.path(), context.getResources().getString(R.string.entitlement_sharing_detected_title), context.getResources().getString(R.string.entitlement_sharing_detected_description)).setPrimaryButton(new DialogButtonText("quit", context.getResources().getString(R.string.launch_check_dialog_generic_quit_button))).setSecondaryButton(new DialogButtonText(SystemDialogActions.LAUNCH_CHECK_DIALOG_DISMISS_ACTION, context.getResources().getString(R.string.launch_check_dialog_generic_dismiss_button))).setControllerBackButton(new DialogButton("close")));
    }
}
