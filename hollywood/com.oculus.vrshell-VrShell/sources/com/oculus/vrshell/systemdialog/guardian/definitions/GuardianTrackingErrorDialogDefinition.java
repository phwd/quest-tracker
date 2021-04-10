package com.oculus.vrshell.systemdialog.guardian.definitions;

import android.content.Context;
import com.oculus.os.SettingsManager;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.vrshell.R;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;

public abstract class GuardianTrackingErrorDialogDefinition extends CustomSystemDialogDefinition {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GuardianTrackingErrorDialogDefinition(Context context, DialogDefinitionCustom dialogDefinitionCustom) {
        super(shouldShowHandTrackingHelp() ? dialogDefinitionCustom.setInformationBox(new DialogInformationBox(context.getResources().getString(R.string.guardian_hand_tracking_help_info_box), DialogIcon.InformationBox.CHECK_ALT)) : dialogDefinitionCustom);
    }

    private static boolean shouldShowHandTrackingHelp() {
        return new SettingsManager().getBoolean("hand_tracking_enabled", false);
    }
}
