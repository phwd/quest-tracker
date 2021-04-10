package com.oculus.systemdialog;

import androidx.annotation.Nullable;

public final class DialogVideo {
    private final DialogHeroSpace mDialogHeroSpace;

    public DialogVideo(String str, float f, @Nullable String str2) {
        this.mDialogHeroSpace = new DialogHeroSpace(str, null, f, str2);
    }

    public DialogVideo setVideoAutoLoops(boolean z) {
        this.mDialogHeroSpace.setVideoAutoLoops(z);
        return this;
    }

    public DialogHeroSpace getDialogHeroSpace() {
        return this.mDialogHeroSpace;
    }
}
