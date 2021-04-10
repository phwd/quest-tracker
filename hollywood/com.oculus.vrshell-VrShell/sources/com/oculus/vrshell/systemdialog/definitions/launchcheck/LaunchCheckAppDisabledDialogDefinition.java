package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public final class LaunchCheckAppDisabledDialogDefinition extends CustomSystemDialogDefinition {
    public LaunchCheckAppDisabledDialogDefinition(Context context, String str) {
        super(getDefinition(context, str));
    }

    private static DialogDefinitionCustom getDefinition(Context context, String str) {
        String appDisplayName = LaunchCheckUtils.getAppDisplayName(context, str);
        return new DialogDefinitionCustom(SystemUXRoute.LAUNCH_CHECK_APP_DISABLED.path(), context.getResources().getString(R.string.launch_check_dialog_generic_error_title), context.getResources().getString(R.string.launch_check_app_disabled_dialog_description, appDisplayName)).setPrimaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.launch_check_dialog_generic_cancel_button))).setSecondaryButton(new DialogButtonText(SystemDialogActions.LAUNCH_CHECK_DIALOG_UNINSTALL_ACTION, context.getResources().getString(R.string.launch_check_app_disabled_dialog_uninstall_button))).setControllerBackButton(new DialogButton("close"));
    }
}
