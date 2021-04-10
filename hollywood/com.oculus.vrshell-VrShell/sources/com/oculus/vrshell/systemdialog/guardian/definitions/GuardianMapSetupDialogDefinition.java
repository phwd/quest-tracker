package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianMapSetupDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianMapSetupDialogDefinition(Context context, boolean z) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.MAP_SETUP), context.getResources().getString(R.string.guardian_new_map), context.getResources().getString(R.string.guardian_new_map_desc)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.START_SETUP, context.getResources().getString(R.string.guardian_create))));
    }
}
