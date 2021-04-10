package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianConfirmDeskGuardianDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianConfirmDeskGuardianDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.DRAW_DESK), context.getResources().getString(R.string.guardian_confirm_desk_title), context.getResources().getString(R.string.guardian_confirm_desk_guardian_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.CONFIRM_DESK_GUARDIAN, context.getResources().getString(R.string.guardian_confirm_desk_confirm_button))));
    }
}
