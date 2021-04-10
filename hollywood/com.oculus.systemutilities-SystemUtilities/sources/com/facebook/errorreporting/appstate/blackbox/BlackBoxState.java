package com.facebook.errorreporting.appstate.blackbox;

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
