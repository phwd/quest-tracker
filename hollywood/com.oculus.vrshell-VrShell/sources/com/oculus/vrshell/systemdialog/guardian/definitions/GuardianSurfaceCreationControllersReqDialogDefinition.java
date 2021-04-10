package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianSurfaceCreationControllersReqDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianSurfaceCreationControllersReqDialogDefinition(Context context, boolean z) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.SURFACE_CREATION_CONTROLLERS_REQ), context.getResources().getString(R.string.guardian_surface_creation_controllers_req_title), context.getResources().getString(R.string.guardian_surface_creation_controllers_req_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.REENABLE_CONTROLLERS, context.getResources().getString(R.string.guardian_surface_creation_controllers_req_continue_button)).setDisabled(z)).setSecondaryButton(new DialogButtonText(GuardianDialogActions.CANCEL_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_surface_creation_controllers_req_cancel_button))));
    }
}
