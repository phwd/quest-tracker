package com.oculus.vrshell.systemdialog.definitions;

import android.content.Context;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.vrshell.R;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.systemdialog.CustomSystemDialogDefinition;
import com.oculus.vrshell.systemdialog.SystemDialogActions;

public final class PanelReorientDialogDefinition extends CustomSystemDialogDefinition {
    public PanelReorientDialogDefinition(Context context) {
        super(new DialogDefinitionCustom(SystemUXRoute.PANEL_REORIENT.path(), context.getResources().getString(R.string.panel_reorient_dialog_title), context.getResources().getString(R.string.panel_reorient_dialog_instructions)).setPrimaryButton(new DialogButtonText(SystemDialogActions.OK_ACTION, context.getResources().getString(R.string.panel_reorient_dialog_button))));
    }
}
