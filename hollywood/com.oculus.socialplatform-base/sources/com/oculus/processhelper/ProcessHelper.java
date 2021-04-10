package com.oculus.processhelper;

import X.AnonymousClass0MD;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.facebook.acra.AppComponentStats;
import com.facebook.inject.ForAppContext;
import javax.annotation.Nullable;

public class ProcessHelper {
    public static final String TAG = "com.oculus.processhelper.ProcessHelper";

    @Nullable
    public static String getCurrentProcessName(@ForAppContext Context context) {
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                AnonymousClass0MD.A0A(TAG, "Current process name is: %s", runningAppProcessInfo.processName);
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public static boolean isInDefaultProcess(Context context) {
        return context.getPackageName().equals(getCurrentProcessName(context));
    }
}
