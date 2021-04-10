package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogDefinitions;
import com.oculus.vrshell.systemdialog.guardian.GuardianDialogType;

public final class GuardianTrackingTooBrightDialogDefinition extends GuardianTrackingErrorDialogDefinition {
    public GuardianTrackingTooBrightDialogDefinition(Context context) {
        super(context, new DialogDefinitionCustom(GuardianDialogDefinitions.getGuardianDialogId(GuardianDialogType.TRACKING_TOO_BRIGHT), context.getResources().getString(R.string.guardian_error_tracking), context.getResources().getString(R.string.guardian_tracking_too_bright)));
    }
}
