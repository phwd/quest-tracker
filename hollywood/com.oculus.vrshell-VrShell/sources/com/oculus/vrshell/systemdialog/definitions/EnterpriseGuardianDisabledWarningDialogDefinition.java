package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public class EnterpriseGuardianDisabledWarningDialogDefinition extends CustomSystemDialogDefinition {
    private static final int DIALOG_AUTO_DISMISS_DELAY_MILLIS = 6000;

    public EnterpriseGuardianDisabledWarningDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.ENTERPRISE_GUARDIAN_DISABLED_WARNING.path(), context.getResources().getString(R.string.enterprise_guardian_disabled_warning_dialog_title), context.getResources().getString(R.string.enterprise_guardian_disabled_warning_dialog_description)).setAutoAction("close", DIALOG_AUTO_DISMISS_DELAY_MILLIS));
    }
}
