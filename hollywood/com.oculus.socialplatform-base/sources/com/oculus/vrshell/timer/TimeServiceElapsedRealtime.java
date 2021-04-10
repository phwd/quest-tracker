package com.oculus.vrshell.timer;

import android.os.SystemClock;

public final class TimeServiceElapsedRealtime implements TimeService {
    public static final TimeService sInstance = new TimeServiceElapsedRealtime();

    public static TimeService getInstance() {
        return sInstance;
    }

    @Override // com.oculus.vrshell.timer.TimeService
    public long getCurrentMs() {
        return SystemClock.elapsedRealtime();
    }
}
