package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public final class LaunchCheckRequiresAvatarDialogDefinition extends CustomSystemDialogDefinition {
    public LaunchCheckRequiresAvatarDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.LAUNCH_CHECK_REQUIRES_AVATAR.path(), context.getResources().getString(R.string.launch_check_requires_avatar_dialog_title), context.getResources().getString(R.string.launch_check_requires_avatar_dialog_description)).setPrimaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.launch_check_dialog_generic_cancel_button))).setSecondaryButton(new DialogButtonText(SystemDialogActions.LAUNCH_CHECK_DIALOG_SETUP_AVATAR_ACTION, context.getResources().getString(R.string.launch_check_requires_avatar_dialog_setup_button))).setControllerBackButton(new DialogButton("close")));
    }
}
