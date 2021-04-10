package com.oculus.processhelper;

import X.AnonymousClass0NK;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import java.util.Iterator;

public class ProcessHelper {
    public static final String TAG = "com.oculus.processhelper.ProcessHelper";

    public static boolean A00(Context context) {
        String str;
        String packageName = context.getPackageName();
        int myPid = Process.myPid();
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().iterator();
        while (true) {
            if (!it.hasNext()) {
                str = null;
                break;
            }
            ActivityManager.RunningAppProcessInfo next = it.next();
            if (next.pid == myPid) {
                AnonymousClass0NK.A07(TAG, "Current process name is: %s", next.processName);
                str = next.processName;
                break;
            }
        }
        return packageName.equals(str);
    }
}
