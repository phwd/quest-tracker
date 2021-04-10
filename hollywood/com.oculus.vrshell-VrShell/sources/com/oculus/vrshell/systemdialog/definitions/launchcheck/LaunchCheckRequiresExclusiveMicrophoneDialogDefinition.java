package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public final class LaunchCheckRequiresExclusiveMicrophoneDialogDefinition extends CustomSystemDialogDefinition {
    public LaunchCheckRequiresExclusiveMicrophoneDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE.path(), context.getResources().getString(R.string.launch_check_requires_exclusive_microphone_dialog_title), context.getResources().getString(R.string.launch_check_requires_exclusive_microphone_dialog_description)).setPrimaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.launch_check_dialog_generic_cancel_button))).setSecondaryButton(new DialogButtonText("continue", context.getResources().getString(R.string.launch_check_dialog_generic_launch_button))).setControllerBackButton(new DialogButton("close")));
    }
}
