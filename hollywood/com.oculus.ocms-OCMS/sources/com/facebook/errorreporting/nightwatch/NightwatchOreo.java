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

    static int recordDataInNightWatch(long j, int i) {
        return nRecordDataInNightWatch(0, j, i);
    }

    static int recordTickInNightWatch(long j, long j2, long j3, long j4) {
        return nRecordTickInNightWatch(0, j, j2, j3, j4);
    }
}
