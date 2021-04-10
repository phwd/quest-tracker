package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public final class LaunchCheckBlockDeskDialogDefinition extends CustomSystemDialogDefinition {
    public LaunchCheckBlockDeskDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.LAUNCH_CHECK_BLOCK_DESK.path(), context.getResources().getString(R.string.launch_check_block_desk_dialog_title), context.getResources().getString(R.string.launch_check_block_desk_dialog_description)).setPrimaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.launch_check_dialog_generic_cancel_button))).setControllerBackButton(new DialogButton("close")));
    }
}
