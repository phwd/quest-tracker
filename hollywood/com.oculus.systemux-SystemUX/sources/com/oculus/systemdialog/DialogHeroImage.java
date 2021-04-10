package com.oculus.systemdialog;

import androidx.annotation.Nullable;

public final class DialogHeroImage {
    private final DialogHeroSpace mDialogHeroSpace;

    public DialogHeroImage(String str, float f, @Nullable String str2) {
        this.mDialogHeroSpace = new DialogHeroSpace(null, str, f, str2);
    }

    public DialogHeroSpace getDialogHeroSpace() {
        return this.mDialogHeroSpace;
    }
}
