package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianPTWarningSetupDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianPTWarningSetupDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.PT_WARNING_SETUP), context.getResources().getString(R.string.guardian_pt_warning_setup_title), context.getResources().getString(R.string.guardian_pt_warning_setup_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.ACKNOWLEDGE_PT_WARNING, context.getResources().getString(R.string.guardian_acknowledge_pt_warning))));
    }
}
