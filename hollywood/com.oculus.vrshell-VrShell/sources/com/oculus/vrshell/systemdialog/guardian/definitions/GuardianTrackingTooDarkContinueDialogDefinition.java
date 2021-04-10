package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianTrackingTooDarkContinueDialogDefinition extends GuardianTrackingErrorDialogDefinition {
    public GuardianTrackingTooDarkContinueDialogDefinition(Context context) {
        super(context, new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.TRACKING_TOO_DARK_CONTINUE), context.getResources().getString(R.string.guardian_error_tracking), context.getResources().getString(R.string.guardian_tracking_too_dark_continue)).setSecondaryButton(new DialogButtonText(GuardianDialogActions.DISABLE_INSIGHT, context.getResources().getString(R.string.guardian_btn_enable_3dof))));
    }
}
