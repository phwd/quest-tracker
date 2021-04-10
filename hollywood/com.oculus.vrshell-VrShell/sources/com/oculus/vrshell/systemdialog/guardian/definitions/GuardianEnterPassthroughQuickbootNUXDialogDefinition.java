package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianEnterPassthroughQuickbootNUXDialogDefinition extends CustomSystemDialogDefinition {
    private static final float VIDEO_ASPECT_RATIO = 1.778f;
    private static final String VIDEO_BACKGROUND_COLOR = "0xFF1A1A1A";

    public GuardianEnterPassthroughQuickbootNUXDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.ENTER_PASSTHROUGH_QUICKBOOT_NUX), context.getResources().getString(R.string.passthrough_quickboot_enabled_title), context.getResources().getString(R.string.passthrough_quickboot_enabled_body)).setPrimaryButton(new DialogButtonText(GuardianDialogActions.ACKNOWLEDGE_QUICKBOOT_NUX, context.getResources().getString(R.string.guardian_acknowledge_quickboot_nux))).setHeroImage(new DialogHeroImage("apk://com.oculus.guardianresources/assets/animatics/QUICKBOOT_01_INTRO.png", VIDEO_ASPECT_RATIO, VIDEO_BACKGROUND_COLOR)));
    }
}
