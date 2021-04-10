package com.oculus.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;

final class SystemClock implements Clock {
    SystemClock() {
    }

    @Override // com.oculus.android.exoplayer2.util.Clock
    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    @Override // com.oculus.android.exoplayer2.util.Clock
    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }

    @Override // com.oculus.android.exoplayer2.util.Clock
    public void sleep(long j) {
        android.os.SystemClock.sleep(j);
    }

    @Override // com.oculus.android.exoplayer2.util.Clock
    public HandlerWrapper createHandler(Looper looper, @Nullable Handler.Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }
}
