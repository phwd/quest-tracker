package com.facebook.common.time;

import com.facebook.common.internal.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface MonotonicNanoClock {
    @DoNotStrip
    long nowNanos();
}
