package com.facebook.acra.perf;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class StartTimeBlockedRecorder {
    public static long sDurationStartupBlocked;
    public static int sTotalCrashesUploaded;

    public static long getDurationStartupBlocked() {
        return sDurationStartupBlocked;
    }

    public static int getTotalCrashesUploaded() {
        return sTotalCrashesUploaded;
    }

    public static void setDurationStartupBlocked(long j) {
        sDurationStartupBlocked = j;
    }

    public static void setTotalCrashesUploaded(int i) {
        sTotalCrashesUploaded = i;
    }
}
