package com.oculus.panelapp.androiddialog;

import androidx.annotation.Nullable;
import com.oculus.systemdialog.DialogDefinitionCustom;

public interface Dialog {
    void destroy();

    @Nullable
    default DialogDefinitionCustom getDialogDefinition() {
        return null;
    }

    boolean onControllerBackButton();
}
