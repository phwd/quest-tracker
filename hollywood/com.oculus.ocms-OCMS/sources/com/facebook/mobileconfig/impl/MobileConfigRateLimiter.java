package com.facebook.mobileconfig.impl;

import com.facebook.common.time.Clock;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigRateLimiter<T> {
    private final Map<T, Long> callTimes = Collections.synchronizedMap(new HashMap());
    private final Clock clock;
    private final long minElapsedTimeMs;

    public MobileConfigRateLimiter(Clock clock2, long j) {
        this.clock = clock2;
        this.minElapsedTimeMs = j;
    }

    public boolean take(T t) {
        long now = this.clock.now();
        Long l = this.callTimes.get(t);
        if (l != null && now - l.longValue() <= this.minElapsedTimeMs) {
            return false;
        }
        this.callTimes.put(t, Long.valueOf(this.clock.now()));
        return true;
    }

    public void clear() {
        this.callTimes.clear();
    }
}
