package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogVideo;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogVideos;

public final class GuardianDrawBoundaryDialogDefinition extends CustomSystemDialogDefinition {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuardianDrawBoundaryDialogDefinition(Context context, boolean z) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.DRAW_BOUNDARY), context.getResources().getString(R.string.guardian_boundary_title), context.getResources().getString(GuardianDialogDefinitions.useImperialMeasurement(context) ? z ? R.string.guardian_boundary_body_hand_imperial : R.string.guardian_boundary_body_imperial : z ? R.string.guardian_boundary_body_hand_metric : R.string.guardian_boundary_body_metric)).setTertiaryButton(new DialogButtonText(GuardianDialogActions.SELECT_STATIONARY, context.getResources().getString(R.string.guardian_btn_skip))).setBackButton(new DialogButton(GuardianDialogActions.START_SETUP)).setVideo(z ? new DialogVideo(GuardianDialogVideos.HANDS_DRAW, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR) : new DialogVideo("apk://com.oculus.guardianresources/assets/animatics/03_DRAW.mp4", 1.65f, GuardianDialogVideos.BACKGROUND_COLOR)));
    }
}
