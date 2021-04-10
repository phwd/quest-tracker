package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianSwitchSpaceDeskAndQuitAppDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianSwitchSpaceDeskAndQuitAppDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.SWITCH_SPACE_DESK), context.getResources().getString(R.string.guardian_switch_space_desk_quit_app_title), context.getResources().getString(R.string.guardian_switch_space_desk_quit_app_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.SWITCH_SPACE_QUIT_APP, context.getResources().getString(R.string.guardian_switch_space_quit_app_button))));
    }
}
