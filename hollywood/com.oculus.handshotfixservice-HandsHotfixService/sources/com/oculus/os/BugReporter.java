package com.oculus.os;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Display;
import oculus.internal.Constants;
import oculus.internal.Gatekeeper;
import oculus.internal.ScreenshotAdapter;

public class BugReporter {
    private static final boolean DEBUG = false;
    private static final String TAG = BugReporter.class.getSimpleName();
    private static final Gatekeeper sBugReportGatekeeperV2 = new Gatekeeper(10);
    private static final Gatekeeper sBugReportGatekeeperV3 = new Gatekeeper(22);
    private static final Intent sLaunchIntent = new Intent();

    static {
        sLaunchIntent.setComponent(ComponentName.createRelative(Constants.SHELL_PACKAGE, ".ShellControlBroadcastReceiver"));
        sLaunchIntent.setAction("com.oculus.vrshell.intent.action.LAUNCH");
        sLaunchIntent.putExtra("intent_data", Uri.parse("systemux://bug_report"));
    }

    @SystemApi
    public static Bitmap screenshot(Display d) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        d.getRealMetrics(displayMetrics);
        float[] dims = {(float) displayMetrics.widthPixels, (float) displayMetrics.heightPixels};
        return new ScreenshotAdapter().screenshot((int) dims[0], (int) dims[1]);
    }

    public static boolean isEnabled() {
        if (sBugReportGatekeeperV2.isEnabled() || sBugReportGatekeeperV3.isEnabled()) {
            return true;
        }
        return DEBUG;
    }

    public static Intent getLaunchIntent() {
        return sLaunchIntent;
    }
}
