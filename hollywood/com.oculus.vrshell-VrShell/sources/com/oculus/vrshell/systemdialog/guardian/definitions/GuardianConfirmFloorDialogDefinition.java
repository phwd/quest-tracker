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

public final class GuardianConfirmFloorDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianConfirmFloorDialogDefinition(Context context, boolean z, boolean z2) {
        super(getDialogDefinition(context, z, z2));
    }

    private static DialogDefinitionCustom getDialogDefinition(Context context, boolean z, boolean z2) {
        Resources resources = context.getResources();
        DialogDefinitionCustom video = new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.CONFIRM_FLOOR), resources.getString(R.string.guardian_setup_confirm_floor), resources.getString(z2 ? R.string.guardian_setup_confirm_floor_body_hand : R.string.guardian_setup_confirm_floor_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.SET_FLOOR_HEIGHT, resources.getString(R.string.guardian_btn_confirm))).setSecondaryButton(new DialogButtonText(GuardianDialogActions.RESET_FLOOR_HEIGHT, resources.getString(R.string.guardian_btn_reset))).setVideo(z2 ? new DialogVideo(GuardianDialogVideos.HANDS_FLOOR_FINDER, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR) : new DialogVideo(GuardianDialogVideos.FLOOR_FINDER, 1.65f, GuardianDialogVideos.BACKGROUND_COLOR));
        if (z) {
            video.setTertiaryButton(new DialogButtonText("cancel", resources.getString(R.string.guardian_btn_cancel)));
        }
        return video;
    }
}
