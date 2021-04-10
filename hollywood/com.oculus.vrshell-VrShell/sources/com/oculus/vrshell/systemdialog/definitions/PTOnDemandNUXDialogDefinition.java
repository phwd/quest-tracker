package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public final class PTOnDemandNUXDialogDefinition extends CustomSystemDialogDefinition {
    public PTOnDemandNUXDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.PT_ONDEMAND_NUX_DIALOG.path(), context.getResources().getString(R.string.pt_ondemand_nux_title), context.getResources().getString(R.string.pt_ondemand_nux_body)).setPrimaryButton(new DialogButtonText("cancel", context.getResources().getString(R.string.pt_ondemand_nux_button))).setHeroImage(new DialogHeroImage("apk://com.oculus.guardianresources/assets/animatics/PTOnDemandNUX.jpg", 1.778f, "0xFF1A1A1A")));
    }
}
