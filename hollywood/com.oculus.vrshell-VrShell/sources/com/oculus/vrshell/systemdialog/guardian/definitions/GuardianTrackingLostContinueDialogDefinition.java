package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogActions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianTrackingLostContinueDialogDefinition extends GuardianTrackingErrorDialogDefinition {
    public GuardianTrackingLostContinueDialogDefinition(Context context) {
        super(context, new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.TRACKING_LOST_CONTINUE), context.getResources().getString(R.string.guardian_error_tracking), context.getResources().getString(R.string.guardian_tracking_lost_body_continue)).setSecondaryButton(new DialogButtonText(GuardianDialogActions.DISABLE_INSIGHT, context.getResources().getString(R.string.guardian_btn_enable_3dof))));
    }
}
