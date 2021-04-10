package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianTrackingBadTextureQuitDialogDefinition extends CustomSystemDialogDefinition {
    public GuardianTrackingBadTextureQuitDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.TRACKING_BAD_TEXTURE_QUIT), context.getResources().getString(R.string.guardian_error_tracking), context.getResources().getString(R.string.guardian_tracking_bad_texture_quit)).setSecondaryButton(new DialogButtonText(GuardianDialogActions.QUIT_APPLICATION, context.getResources().getString(R.string.guardian_btn_quit_app))));
    }
}
