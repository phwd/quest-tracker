package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianExistingPassthroughPortalDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianExistingPassthroughPortalDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.DRAW_PT_PORTAL), context.getResources().getString(R.string.guardian_existing_pt_portal_title), context.getResources().getString(R.string.guardian_existing_pt_portal_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.REDRAW_PT_PORTAL, context.getResources().getString(R.string.guardian_confirm_pt_portal_redraw_button))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.DELETE_PT_PORTAL, context.getResources().getString(R.string.guardian_remove_pt_portal_button))).setTertiaryButton(new DialogButtonText(GuardianDialogActions.CANCEL_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_cancel_pt_portal_flow_button))).setControllerBackButton(new DialogButton(GuardianDialogActions.CANCEL_SURFACE_DRAWING)));
    }
}
