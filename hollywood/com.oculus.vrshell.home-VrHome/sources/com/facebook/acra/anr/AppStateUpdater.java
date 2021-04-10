package com.facebook.acra.anr;

import com.facebook.reliability.anr.AnrState;
import javax.annotation.Nullable;

public abstract class AppStateUpdater {
    public void updateAnrState(AnrState state) {
        throw new AbstractMethodError("Method needs to be overridden");
    }

    public void updateAnrState(AnrState state, @Nullable Runnable afterUpdateRunnable) {
        updateAnrState(state);
        if (afterUpdateRunnable != null) {
            afterUpdateRunnable.run();
        }
    }

    public void updateAnrState(AnrState state, @Nullable Runnable afterUpdateRunnable, boolean inForeground) {
        updateAnrState(state, afterUpdateRunnable);
    }

    public boolean isAppInForegroundV1() {
        return false;
    }

    public boolean isAppInForegroundV2() {
        return false;
    }
}
