package oculus.internal;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;

public class ActivityUtils {
    private static final String TAG = ActivityUtils.class.getSimpleName();

    public static boolean startActivityFromRecents(Intent intent, Bundle options) {
        if (intent == null || intent.getComponent() == null) {
            return false;
        }
        ComponentName cn = intent.getComponent();
        try {
            for (ActivityManager.RecentTaskInfo t : ActivityTaskManager.getService().getRecentTasks(100, 1, UserHandle.myUserId()).getList()) {
                ComponentName baseIntentComponent = t.baseIntent.getComponent();
                ComponentName baseActivity = t.baseActivity;
                int taskId = t.id;
                if (!cn.equals(baseIntentComponent)) {
                    if (cn.equals(baseActivity)) {
                    }
                }
                if (taskId <= 0) {
                    return false;
                }
                ActivityTaskManager.getService().startActivityFromRecents(taskId, options);
                String str = TAG;
                Log.i(str, "Starting activity from recents: " + intent);
                return true;
            }
        } catch (Exception e) {
            String str2 = TAG;
            Log.w(str2, "Error launching from recents " + e);
        }
        return false;
    }
}
