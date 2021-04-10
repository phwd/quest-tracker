package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianControllerRequiredPassthroughPortalDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianControllerRequiredPassthroughPortalDialogDefinition(Context context, boolean z) {
        super(getDialogDefinition(context, z));
    }

    private static DialogDefinitionCustom getDialogDefinition(Context context, boolean z) {
        context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.CONTROLLER_REQ_PT_PORTAL), context.getResources().getString(R.string.controllers_req_title), context.getResources().getString(R.string.controllers_req_pt_portal_desc));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText(GuardianDialogActions.CONTROLLER_BACK_PT_PORTAL, context.getResources().getString(R.string.controllers_req_back_button)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(GuardianDialogActions.CONTROLLER_CONT_PT_PORTAL, context.getResources().getString(R.string.controllers_req_cont_button)).setDisabled(z));
        return dialogDefinitionCustom;
    }
}
