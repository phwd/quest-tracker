package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianAdjustDeskHeightDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianAdjustDeskHeightDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.ADJUST_DESK_HEIGHT), context.getResources().getString(R.string.guardian_adjust_desk_height_title), context.getResources().getString(R.string.guardian_adjust_desk_height_body_controller)).setControllerBackButton(new DialogButton(GuardianDialogActions.REDRAW_DESK)).setHeroImage(new DialogHeroImage("apk://com.oculus.guardianresources/assets/animatics/DESK_HEIGHT_ADJUSTMENT.jpg", 1.778f, "0xFF1A1A1A")));
    }
}
