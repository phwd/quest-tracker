package com.facebook.errorreporting.appstate.blackbox;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class BlackBoxState {
    private static BlackBoxState sInstance;
    @Nullable
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

    @Nullable
    public synchronized BlackBoxRecorderControl getBlackBoxRecorderControl() {
        return this.mBlackBoxRecorderControl;
    }
}
