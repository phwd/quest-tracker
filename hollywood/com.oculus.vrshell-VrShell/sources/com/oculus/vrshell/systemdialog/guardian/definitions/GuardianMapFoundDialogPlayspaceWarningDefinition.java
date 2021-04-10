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

public final class GuardianMapFoundDialogPlayspaceWarningDefinition extends CustomSystemDialogDefinition {
    public GuardianMapFoundDialogPlayspaceWarningDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.MAP_FOUND_PLAYSPACE_WARNING), context.getResources().getString(R.string.guardian_playspace_warning_title), context.getResources().getString(R.string.guardian_playspace_warning)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.CONFIRM, context.getResources().getString(R.string.guardian_btn_acknowledge))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.FORCE_SETUP, context.getResources().getString(R.string.guardian_btn_try_again))).setVideo(new DialogVideo(GuardianDialogVideos.PSS_WARNING, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR)));
    }
}
