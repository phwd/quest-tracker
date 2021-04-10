package com.facebook.errorreporting.appstate.blackbox;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class BlackBoxState {
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

    public synchronized BlackBoxRecorderControl getBlackBoxRecorderControl() {
        return this.mBlackBoxRecorderControl;
    }
}
