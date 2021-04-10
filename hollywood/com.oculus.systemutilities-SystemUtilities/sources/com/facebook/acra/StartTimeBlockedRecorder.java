package com.facebook.acra;

public class StartTimeBlockedRecorder {
    private static long sDurationStartupBlocked;
    private static int sTotalCrashesUploaded;

    public static void setDurationStartupBlocked(long durationStartupBlocked) {
        sDurationStartupBlocked = durationStartupBlocked;
    }

    public static void setTotalCrashesUploaded(int totalCrashesUploaded) {
        sTotalCrashesUploaded = totalCrashesUploaded;
    }
}
