package defpackage;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.Log;
import org.chromium.base.PackageManagerUtils;

/* renamed from: u6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5028u6 {
    public static String a(ActivityManager.AppTask appTask) {
        Intent intent;
        ActivityManager.RecentTaskInfo b = b(appTask);
        if (b == null || (intent = b.baseIntent) == null) {
            return null;
        }
        if (intent.getComponent() != null) {
            return intent.getComponent().getClassName();
        }
        ResolveInfo d = PackageManagerUtils.d(intent, 0);
        if (d == null) {
            return null;
        }
        return d.activityInfo.name;
    }

    public static ActivityManager.RecentTaskInfo b(ActivityManager.AppTask appTask) {
        try {
            return appTask.getTaskInfo();
        } catch (IllegalArgumentException e) {
            Log.e("DocumentUtilities", "Failed to retrieve task info: ", e);
            return null;
        }
    }
}
