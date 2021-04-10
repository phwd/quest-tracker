package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianControllerRequiredDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianControllerRequiredDialogDefinition(Context context, boolean z, boolean z2) {
        super(getDialogDefinition(context, z, z2));
    }

    private static DialogDefinitionCustom getDialogDefinition(Context context, boolean z, boolean z2) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(z2 ? GuardianDialogType.SETUP_CONTROLLER_REQ : GuardianDialogType.SETUP_CONTROLLER_REQ_EARLY), context.getResources().getString(R.string.controllers_req_title), context.getResources().getString(R.string.controllers_req_desc));
        if (z2) {
            dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(GuardianDialogActions.START_SETUP, context.getResources().getString(R.string.controllers_req_cont_button)).setDisabled(z));
            dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.guardian_btn_cancel)));
        } else {
            dialogDefinitionCustom.setSecondaryButton(new DialogButtonText(GuardianDialogActions.CONTROLLER_BACK, context.getResources().getString(R.string.controllers_req_back_button)));
            dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(GuardianDialogActions.CONTROLLER_CONT, context.getResources().getString(R.string.controllers_req_cont_button)).setDisabled(z));
        }
        return dialogDefinitionCustom;
    }
}
