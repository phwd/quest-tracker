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

public final class GuardianBoundaryCompletePlayspaceClearDialogDefinition extends CustomSystemDialogDefinition {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuardianBoundaryCompletePlayspaceClearDialogDefinition(Context context, boolean z) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.BOUNDARY_COMPLETE_PLAYSPACE_CLEAR), context.getResources().getString(R.string.guardian_roomscale_confirm_title), context.getResources().getString(R.string.guardian_roomscale_complete_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.SETUP_COMPLETE, context.getResources().getString(R.string.guardian_btn_confirm))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.START_BOUNDARY_MODE, context.getResources().getString(R.string.guardian_btn_try_again))).setBackButton(new DialogButton(GuardianDialogActions.START_BOUNDARY_MODE)).setVideo(z ? new DialogVideo(GuardianDialogVideos.HANDS_DRAW, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR) : new DialogVideo("apk://com.oculus.guardianresources/assets/animatics/03_DRAW.mp4", 1.65f, GuardianDialogVideos.BACKGROUND_COLOR)));
    }
}
