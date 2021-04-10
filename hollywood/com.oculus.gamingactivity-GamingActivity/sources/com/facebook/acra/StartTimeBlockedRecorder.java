package com.facebook.acra;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class StartTimeBlockedRecorder {
    private static long sDurationStartupBlocked;
    private static int sTotalCrashesUploaded;

    public static long getDurationStartupBlocked() {
        return sDurationStartupBlocked;
    }

    public static void setDurationStartupBlocked(long durationStartupBlocked) {
        sDurationStartupBlocked = durationStartupBlocked;
    }

    public static int getTotalCrashesUploaded() {
        return sTotalCrashesUploaded;
    }

    public static void setTotalCrashesUploaded(int totalCrashesUploaded) {
        sTotalCrashesUploaded = totalCrashesUploaded;
    }
}
