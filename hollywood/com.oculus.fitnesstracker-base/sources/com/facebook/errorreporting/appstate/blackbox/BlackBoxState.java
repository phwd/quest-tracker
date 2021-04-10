package com.facebook.errorreporting.appstate.blackbox;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class BlackBoxState {
    private static BlackBoxState sInstance;
    private BlackBoxRecorderControl mBlackBoxRecorderControl;

    public static synchronized BlackBoxState getInstance() {
        BlackBoxState blackBoxState;
        synchronized (BlackBoxState.class) {
            if (sInstance == null) {
                sInstance = new BlackBoxState();
            }
            blackBoxState = sInstance;
        }
        return blackBoxState;
    }

    public final synchronized BlackBoxRecorderControl getBlackBoxRecorderControl() {
        return this.mBlackBoxRecorderControl;
    }
}
