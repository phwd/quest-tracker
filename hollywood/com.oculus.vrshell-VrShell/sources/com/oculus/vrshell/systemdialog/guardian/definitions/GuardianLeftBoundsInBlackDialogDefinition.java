package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonPrimary;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogPrimaryButtonStyle;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianLeftBoundsInBlackDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianLeftBoundsInBlackDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.LEFT_BOUNDS), context.getResources().getString(R.string.guardian_oob_title_in_black), context.getResources().getString(R.string.guardian_oob_body_in_black)).setPrimaryButton(new DialogButtonPrimary(GuardianDialogActions.FORCE_SETUP, context.getResources().getString(R.string.guardian_btn_roomscale), DialogPrimaryButtonStyle.SECONDARY)).setSecondaryButton(new DialogButtonText(GuardianDialogActions.SWITCH_TO_STATIONARY, context.getResources().getString(R.string.guardian_btn_stationary))));
    }
}
