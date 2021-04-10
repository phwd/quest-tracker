package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonPrimary;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogPrimaryButtonStyle;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianLeftBoundsInQuickbootDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianLeftBoundsInQuickbootDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.LEFT_BOUNDS_IN_QUICKBOOT), context.getResources().getString(R.string.guardian_oob_title_in_black), context.getResources().getString(R.string.guardian_oob_quickboot_body)).setPrimaryButton(new DialogButtonPrimary(GuardianDialogActions.RECENTER_IN_QUICKBOOT, context.getResources().getString(R.string.guardian_recenter_button), DialogPrimaryButtonStyle.SECONDARY)));
    }
}
