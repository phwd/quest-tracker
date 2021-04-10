package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianTrackingTooDarkQuitDialogDefinition extends GuardianTrackingErrorDialogDefinition {
    public GuardianTrackingTooDarkQuitDialogDefinition(Context context) {
        super(context, new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.TRACKING_TOO_DARK_QUIT), context.getResources().getString(R.string.guardian_error_tracking), context.getResources().getString(R.string.guardian_tracking_too_dark_quit)).setSecondaryButton(new DialogButtonText(GuardianDialogActions.QUIT_APPLICATION, context.getResources().getString(R.string.guardian_btn_quit_app))));
    }
}
