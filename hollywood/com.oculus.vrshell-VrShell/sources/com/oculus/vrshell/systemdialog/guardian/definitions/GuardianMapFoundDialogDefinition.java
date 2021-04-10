package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianMapFoundDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianMapFoundDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.MAP_FOUND), context.getResources().getString(R.string.guardian_map_found), context.getResources().getString(R.string.guardian_map_found_desc)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.CONFIRM, context.getResources().getString(R.string.guardian_btn_confirm_guardian))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.FORCE_SETUP, context.getResources().getString(R.string.guardian_btn_change))));
    }
}
