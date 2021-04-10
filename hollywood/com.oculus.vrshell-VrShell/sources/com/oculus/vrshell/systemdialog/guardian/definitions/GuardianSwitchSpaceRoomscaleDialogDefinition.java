package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianSwitchSpaceRoomscaleDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianSwitchSpaceRoomscaleDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.SWITCH_SPACE_ROOMSCALE), context.getResources().getString(R.string.guardian_switch_space_roomscale_title), context.getResources().getString(R.string.guardian_switch_space_roomscale_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.SWITCH_SPACE, context.getResources().getString(R.string.guardian_switch_space_confirm_button))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.FORCE_SETUP, context.getResources().getString(R.string.guardian_btn_try_again))));
    }
}
