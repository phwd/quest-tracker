package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogVideo;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogVideos;

public final class GuardianWelcomeDialogDefinition extends CustomSystemDialogDefinition {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuardianWelcomeDialogDefinition(Context context, boolean z) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.GUARDIAN_WELCOME), context.getResources().getString(R.string.guardian_welcome_title), context.getResources().getString(z ? R.string.guardian_welcome_body_hand : R.string.guardian_welcome_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.START_SETUP, context.getResources().getString(R.string.guardian_btn_continue))).setVideo(z ? new DialogVideo(GuardianDialogVideos.HANDS_CONTROLLER, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR) : new DialogVideo(GuardianDialogVideos.CONTROLLER, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR)));
    }
}
