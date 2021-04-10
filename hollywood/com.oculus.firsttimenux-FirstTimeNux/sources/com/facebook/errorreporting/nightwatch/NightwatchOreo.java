package com.facebook.errorreporting.nightwatch;

import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;

@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class NightwatchOreo {
    private static native int nRecordDataInNightWatch(long j, long j2, int i);

    private static native int nRecordTickInNightWatch(long j, long j2, long j3, long j4, long j5);

    private NightwatchOreo() {
    }

    static int recordDataInNightWatch(long capacity, int type) {
        return nRecordDataInNightWatch(0, capacity, type);
    }

    static int recordTickInNightWatch(long actualUptimeMs, long expectedUptimeMs, long nextExpectedUptimeMs, long relativeThreadTimeMs) {
        return nRecordTickInNightWatch(0, actualUptimeMs, expectedUptimeMs, nextExpectedUptimeMs, relativeThreadTimeMs);
    }
}
