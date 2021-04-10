package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianQuitAppToPassthroughDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianQuitAppToPassthroughDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.QUIT_APP_TO_PASSTHROUGH), context.getResources().getString(R.string.guardian_quit_app_to_quickboot_title), context.getResources().getString(R.string.guardian_quit_app_to_quickboot_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.FORCE_QUIT_TO_PASSTHROUGH, context.getResources().getString(R.string.guardian_quit_app_to_quickboot_button))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.CANCEL_QUIT_TO_PASSTHROUGH, context.getResources().getString(R.string.guardian_enter_passthrough_quickboot_button))));
    }
}
