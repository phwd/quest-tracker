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

public final class GuardianConfirmSurfaceEditingDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianConfirmSurfaceEditingDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.CONFIRM_SURFACE_EDITING), context.getResources().getString(R.string.guardian_confirm_surface_editing_title), context.getResources().getString(R.string.guardian_confirm_surface_editing_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.EXIT_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_confirm_surface_editing_button))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.CANCEL_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_cancel_surface_editing_button))).setControllerBackButton(new DialogButton(GuardianDialogActions.CANCEL_SURFACE_DRAWING)));
    }
}
