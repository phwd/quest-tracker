package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianMapFoundWithSurfacesDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianMapFoundWithSurfacesDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.MAP_FOUND_WITH_SURFACES), context.getResources().getString(R.string.guardian_map_found_with_surfaces), context.getResources().getString(R.string.guardian_map_found_with_surfaces_desc)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.CONFIRM, context.getResources().getString(R.string.guardian_btn_confirm))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.FORCE_SETUP, context.getResources().getString(R.string.guardian_btn_try_again))));
    }
}
