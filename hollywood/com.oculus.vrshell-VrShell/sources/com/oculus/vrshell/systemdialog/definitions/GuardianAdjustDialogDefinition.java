package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonPrimary;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogPrimaryButtonStyle;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public final class GuardianAdjustDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianAdjustDialogDefinition(Context context) {
        super(createGuardianAdjustDialogDefinitionCustom(context));
    }

    private static DialogDefinitionCustom createGuardianAdjustDialogDefinitionCustom(Context context) {
        return new DialogDefinitionCustom(SystemUXRoute.GUARDIAN_ADJUST_DIALOG.path(), context.getResources().getString(R.string.guardian_adjust_dialog_title), context.getResources().getString(R.string.guardian_adjust_dialog_description)).setPrimaryButton(new DialogButtonPrimary(SystemDialogActions.GUARDIAN_ADJUST_DIALOG_ROOMSCALE_ACTION, context.getResources().getString(R.string.guardian_adjust_dialog_roomscale), DialogPrimaryButtonStyle.SECONDARY)).setSecondaryButton(new DialogButtonText(SystemDialogActions.GUARDIAN_ADJUST_DIALOG_STATIONARY_ACTION, context.getResources().getString(R.string.guardian_adjust_dialog_stationary))).setTertiaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.guardian_adjust_dialog_cancel))).setControllerBackButton(new DialogButton("cancel"));
    }
}
