package defpackage;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.view.Display;
import java.util.ArrayList;
import java.util.List;

/* renamed from: i7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2983i7 {
    public static List a(Activity activity) {
        DisplayManager displayManager;
        ArrayList arrayList = new ArrayList();
        if (activity == null || (displayManager = (DisplayManager) activity.getSystemService("display")) == null) {
            return arrayList;
        }
        Display[] displays = displayManager.getDisplays();
        ActivityManager activityManager = (ActivityManager) activity.getSystemService("activity");
        for (Display display : displays) {
            if (display.getState() == 2 && activityManager.isActivityStartAllowedOnDisplay(activity, display.getDisplayId(), new Intent(activity, activity.getClass()))) {
                arrayList.add(Integer.valueOf(display.getDisplayId()));
            }
        }
        return arrayList;
    }

    public static boolean b() {
        return ActivityManager.isRunningInUserTestHarness();
    }
}
