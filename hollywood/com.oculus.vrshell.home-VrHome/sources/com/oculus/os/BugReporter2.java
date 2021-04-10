package com.oculus.os;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import java.io.File;

public class BugReporter2 {
    BugReporter2(Context context, String bugId) {
        throw new RuntimeException("Stub!");
    }

    public static BugReporter2 createBugReport(Context context) {
        throw new RuntimeException("Stub!");
    }

    public static boolean isBugReporter2Enabled() {
        throw new RuntimeException("Stub!");
    }

    public void storeScreenshot(Bitmap screenshot) {
        throw new RuntimeException("Stub!");
    }

    public void maybeStorePastAudioData() {
        throw new RuntimeException("Stub!");
    }

    public void storeExtraFile(File file) {
        throw new RuntimeException("Stub!");
    }

    public Intent getLaunchIntent(String source, String sourceApp) {
        throw new RuntimeException("Stub!");
    }
}
