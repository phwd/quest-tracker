package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogVideo;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogVideos;

public final class GuardianStationaryCompleteDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianStationaryCompleteDialogDefinition(Context context, boolean z, boolean z2, boolean z3) {
        super(getDialogDefinition(context, z, z2, z3));
    }

    private static DialogDefinitionCustom getDialogDefinition(Context context, boolean z, boolean z2, boolean z3) {
        Resources resources = context.getResources();
        DialogDefinitionCustom video = new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.STATIONARY_COMPLETE), context.getResources().getString(R.string.guardian_stationary_complete), context.getResources().getString(z3 ? R.string.guardian_stationary_v2_complete_body : R.string.guardian_stationary_complete_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.STATIONARY_COMPLETE, resources.getString(R.string.guardian_btn_confirm)).setDisabledUntilScrolledToBottom()).setVideo(z3 ? new DialogVideo(GuardianDialogVideos.STATIONARY_V2_SETUP, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR) : z2 ? new DialogVideo(GuardianDialogVideos.HANDS_STATIONARY_PLAY, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR) : new DialogVideo(GuardianDialogVideos.STATIONARY_PLAY, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR));
        if (z) {
            video.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.guardian_btn_cancel)));
        }
        video.setTertiaryButton(new DialogButtonText(GuardianDialogActions.STATIONARY_TO_ROOMSCALE, resources.getString(R.string.guardian_stationary_complete_back)));
        return video;
    }
}
