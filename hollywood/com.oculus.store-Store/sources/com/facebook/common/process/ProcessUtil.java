package com.facebook.common.process;

import android.app.ActivityManager;

public interface ProcessUtil {
    ProcessName getNameOfCurrentProcess();

    ProcessName getNameOfDefaultProcess();

    ProcessName getNameOfProcess(int i);

    int getPidOfProcessName(String str);

    ActivityManager.RunningAppProcessInfo getProcessInfo(int i);

    boolean isMyProcessByManifestProcessName(String str);

    boolean isMyProcessByProcessName(String str);
}
