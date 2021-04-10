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

public final class GuardianSizeNotOptimalDialogDefinition extends CustomSystemDialogDefinition {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuardianSizeNotOptimalDialogDefinition(Context context, boolean z) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.SIZE_NOT_OPTIMAL), context.getResources().getString(R.string.guardian_boundary_title), context.getResources().getString(GuardianDialogDefinitions.useImperialMeasurement(context) ? R.string.guardian_setup_not_optimal_imperial : R.string.guardian_setup_not_optimal_metric)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.COMPLETE_DRAWING, context.getResources().getString(R.string.guardian_btn_continue))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.START_BOUNDARY_MODE, context.getResources().getString(R.string.guardian_btn_try_again))).setBackButton(new DialogButton(GuardianDialogActions.START_BOUNDARY_MODE)).setVideo(z ? new DialogVideo(GuardianDialogVideos.HANDS_DRAW_ADD, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR) : new DialogVideo(GuardianDialogVideos.DRAW_ADD, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR)));
    }
}
