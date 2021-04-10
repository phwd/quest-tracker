package com.facebook.common.process;

import android.text.TextUtils;
import com.facebook.common.activitythreadholder.ActivityThreadHolder;
import com.facebook.common.procread.ProcReader;
import com.oculus.common.build.BuildConfig;

public class ProcessName {
    private static volatile ProcessName sCachedProcessName;
    private final String mFullProcessName;
    private final PrivateProcessName mPrivateProcessName;

    public ProcessName() {
        this(null, null);
    }

    private ProcessName(String fullProcessName, PrivateProcessName privateProcessName) {
        this.mFullProcessName = fullProcessName;
        this.mPrivateProcessName = privateProcessName;
    }

    public static ProcessName create(String processName) {
        String shortPrivateProcessName;
        if (processName == null) {
            return new ProcessName(null, null);
        }
        String[] parts = processName.split(":");
        if (parts.length > 1) {
            shortPrivateProcessName = parts[1];
        } else {
            shortPrivateProcessName = BuildConfig.PROVIDER_SUFFIX;
        }
        return new ProcessName(processName, PrivateProcessName.createFromShortName(shortPrivateProcessName));
    }

    public String getFullName() {
        return this.mFullProcessName;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProcessName that = (ProcessName) o;
        if (this.mFullProcessName == null) {
            return that.mFullProcessName == null;
        }
        return this.mFullProcessName.equals(that.mFullProcessName);
    }

    public int hashCode() {
        if (this.mFullProcessName != null) {
            return this.mFullProcessName.hashCode();
        }
        return 0;
    }

    public String toString() {
        return this.mFullProcessName == null ? "<unknown>" : this.mFullProcessName;
    }

    public static ProcessName current() {
        ProcessName processName = sCachedProcessName;
        if (processName != null) {
            return processName;
        }
        ProcessName processName2 = create(ActivityThreadHolder.getActivityThread().getProcessName());
        sCachedProcessName = processName2;
        if (!TextUtils.isEmpty(processName2.getFullName())) {
            return processName2;
        }
        ProcessName processName3 = getProcessNameFromProcFS();
        if (processName3 == null) {
            return sCachedProcessName;
        }
        sCachedProcessName = processName3;
        return processName3;
    }

    private static ProcessName getProcessNameFromProcFS() {
        String cmdLine = ProcReader.readProcFileEntirely("/proc/self/cmdline");
        if (TextUtils.isEmpty(cmdLine)) {
            return null;
        }
        return create(cmdLine);
    }
}
