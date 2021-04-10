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

public final class GuardianConfirmWhiteboardDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianConfirmWhiteboardDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.DRAW_WHITEBOARD), context.getResources().getString(R.string.guardian_confirm_whiteboard_title), context.getResources().getString(R.string.guardian_confirm_whiteboard_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.EXIT_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_confirm_whiteboard_confirm_button))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.REDRAW_WHITEBOARD, context.getResources().getString(R.string.guardian_confirm_whiteboard_redraw_button))).setTertiaryButton(new DialogButtonText(GuardianDialogActions.CANCEL_SURFACE_DRAWING, context.getResources().getString(R.string.guardian_cancel_whiteboard_flow_button))).setControllerBackButton(new DialogButton(GuardianDialogActions.REDRAW_WHITEBOARD)));
    }
}
