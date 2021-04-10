package com.oculus.vrshell.systemdialog.definitions.launchcheck;

import android.content.Context;
import android.text.Html;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public final class LaunchCheckNonCompliantDialogDefinition extends CustomSystemDialogDefinition {
    public LaunchCheckNonCompliantDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.LAUNCH_CHECK_NON_COMPLIANT.path(), context.getResources().getString(R.string.launch_check_non_compliant_dialog_title), getDescriptionHtmlString(context)).setPrimaryButton(new DialogButtonText("continue", context.getResources().getString(R.string.launch_check_dialog_generic_continue_button))).setSecondaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.launch_check_dialog_generic_cancel_button))).setControllerBackButton(new DialogButton("close")));
    }

    private static String getDescriptionHtmlString(Context context) {
        return Html.toHtml(Html.fromHtml(context.getResources().getString(R.string.launch_check_non_compliant_dialog_description), 0), 0);
    }
}
