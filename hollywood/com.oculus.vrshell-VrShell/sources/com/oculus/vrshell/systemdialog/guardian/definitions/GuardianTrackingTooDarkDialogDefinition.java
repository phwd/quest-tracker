package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianTrackingTooDarkDialogDefinition extends GuardianTrackingErrorDialogDefinition {
    public GuardianTrackingTooDarkDialogDefinition(Context context) {
        super(context, new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.TRACKING_TOO_DARK), context.getResources().getString(R.string.guardian_error_tracking), context.getResources().getString(R.string.guardian_tracking_too_dark)));
    }
}
