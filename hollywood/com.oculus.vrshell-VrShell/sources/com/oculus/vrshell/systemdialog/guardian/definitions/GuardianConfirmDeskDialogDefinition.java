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

public final class GuardianConfirmDeskDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianConfirmDeskDialogDefinition(Context context, boolean z) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.DRAW_DESK), context.getResources().getString(R.string.guardian_confirm_desk_title), context.getResources().getString(R.string.guardian_confirm_desk_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.EXIT_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_confirm_desk_confirm_button))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.REDRAW_DESK, context.getResources().getString(R.string.guardian_confirm_desk_redraw_button))).setTertiaryButton(new DialogButtonText(GuardianDialogActions.CANCEL_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_cancel_desk_flow_button))).setControllerBackButton(createButtonToPreviousStep(z)));
    }

    private static DialogButton createButtonToPreviousStep(boolean z) {
        return z ? new DialogButton(GuardianDialogActions.ADJUST_DESK_HEIGHT) : new DialogButton(GuardianDialogActions.REDRAW_DESK);
    }
}
