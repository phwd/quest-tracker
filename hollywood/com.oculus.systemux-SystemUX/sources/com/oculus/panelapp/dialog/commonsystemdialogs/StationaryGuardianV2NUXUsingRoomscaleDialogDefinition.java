package com.oculus.panelapp.dialog.commonsystemdialogs;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogVideo;
import com.oculus.vrshell.SystemUXRoute;

public final class StationaryGuardianV2NUXUsingRoomscaleDialogDefinition extends CommonDialog {
    private static final float VIDEO_ASPECT_RATIO = 1.65f;
    private static final String VIDEO_BACKGROUND_COLOR = "0xFF000000";
    private final Resources mResources;

    public StationaryGuardianV2NUXUsingRoomscaleDialogDefinition(Context context) {
        this.mResources = context.getResources();
        setPendingDialogConfiguration(getStationaryGuardianV2NuxIntroDialog());
    }

    /* access modifiers changed from: package-private */
    public DialogDefinitionCustom getStationaryGuardianV2NuxIntroDialog() {
        return new DialogDefinitionCustom(SystemUXRoute.STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE.path(), this.mResources.getString(R.string.stationary_guardian_v2_nux_title), this.mResources.getString(R.string.stationary_guardian_v2_nux_body_roomscale)).setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.SWITCH_TO_STATIONARY, this.mResources.getString(R.string.stationary_guardian_v2_nux_button_switch_to_stationary))).setSecondaryButton(new DialogButtonText("close", this.mResources.getString(R.string.stationary_guardian_v2_nux_button_dismiss))).setVideo(new DialogVideo("apk://com.oculus.guardianresources/assets/animatics/STATIONARY_V2.mp4", VIDEO_ASPECT_RATIO, "0xFF000000"));
    }
}
