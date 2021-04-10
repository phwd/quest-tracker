package com.oculus.processhelper;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.facebook.debug.log.BLog;
import com.facebook.inject.ForAppContext;
import javax.annotation.Nullable;

public class ProcessHelper {
    private static final String TAG = "com.oculus.processhelper.ProcessHelper";

    public static boolean isInDefaultProcess(Context context) {
        return context.getPackageName().equals(getCurrentProcessName(context));
    }

    @Nullable
    private static String getCurrentProcessName(@ForAppContext Context context) {
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                BLog.w(TAG, "Current process name is: %s", runningAppProcessInfo.processName);
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }
}
