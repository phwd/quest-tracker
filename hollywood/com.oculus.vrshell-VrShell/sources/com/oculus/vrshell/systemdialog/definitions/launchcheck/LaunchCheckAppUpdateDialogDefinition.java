package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public final class LaunchCheckAppUpdateDialogDefinition extends CustomSystemDialogDefinition {
    public LaunchCheckAppUpdateDialogDefinition(Context context, String str) {
        super(getDefinition(context, str));
    }

    private static DialogDefinitionCustom getDefinition(Context context, String str) {
        String appDisplayName = LaunchCheckUtils.getAppDisplayName(context, str);
        return new DialogDefinitionCustom(SystemUXRoute.LAUNCH_CHECK_APP_UPDATE.path(), context.getResources().getString(R.string.launch_check_dialog_generic_warning_title), context.getResources().getString(R.string.launch_check_app_update_dialog_description, appDisplayName)).setPrimaryButton(new DialogButtonText("update", context.getResources().getString(R.string.launch_check_app_update_dialog_update_button))).setSecondaryButton(new DialogButtonText("continue", context.getResources().getString(R.string.launch_check_dialog_generic_launch_button))).setTertiaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.launch_check_dialog_generic_cancel_button))).setControllerBackButton(new DialogButton("close"));
    }
}
