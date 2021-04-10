package com.oculus.vrshell.timer;

import android.os.SystemClock;

public final class TimeServiceElapsedRealtime implements TimeService {
    private static final TimeService sInstance = new TimeServiceElapsedRealtime();

    @Override // com.oculus.vrshell.timer.TimeService
    public long getCurrentMs() {
        return SystemClock.elapsedRealtime();
    }

    public static TimeService getInstance() {
        return sInstance;
    }
}
