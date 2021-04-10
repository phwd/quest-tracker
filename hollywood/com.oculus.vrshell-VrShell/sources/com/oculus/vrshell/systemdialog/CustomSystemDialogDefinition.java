package com.oculus.vrshell.systemdialog;

import com.oculus.systemdialog.DialogDefinitionCustom;

public class CustomSystemDialogDefinition {
    private final String mDialogDefinition;

    public CustomSystemDialogDefinition(DialogDefinitionCustom dialogDefinitionCustom) {
        this.mDialogDefinition = dialogDefinitionCustom.getDialogConfigurationIPCParcel();
    }

    public final synchronized String getPendingDialogConfigurationIPCParcel() {
        return this.mDialogDefinition;
    }
}
