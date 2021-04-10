package com.oculus.systemdialog;

import androidx.annotation.Nullable;

public final class DialogVideo {
    public final DialogHeroSpace mDialogHeroSpace;

    public DialogHeroSpace getDialogHeroSpace() {
        return this.mDialogHeroSpace;
    }

    public DialogVideo setVideoAutoLoops(boolean z) {
        this.mDialogHeroSpace.mVideoAutoLoops = z;
        return this;
    }

    public DialogVideo(String str, float f, @Nullable String str2) {
        this.mDialogHeroSpace = new DialogHeroSpace(str, null, f, str2);
    }
}
