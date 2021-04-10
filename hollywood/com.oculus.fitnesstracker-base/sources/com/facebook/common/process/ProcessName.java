package com.facebook.common.process;

import android.text.TextUtils;
import com.facebook.common.activitythreadholder.ActivityThreadHolder;
import com.facebook.common.procread.ProcReader;
import com.oculus.common.build.BuildConfig;

public final class ProcessName {
    private static volatile ProcessName sCachedProcessName;
    public final String mFullProcessName;
    private final PrivateProcessName mPrivateProcessName;

    public ProcessName() {
        this(null, null);
    }

    private ProcessName(String str, PrivateProcessName privateProcessName) {
        this.mFullProcessName = str;
        this.mPrivateProcessName = privateProcessName;
    }

    private static ProcessName create(String str) {
        if (str == null) {
            return new ProcessName(null, null);
        }
        String[] split = str.split(":");
        return new ProcessName(str, PrivateProcessName.createFromShortName(split.length > 1 ? split[1] : BuildConfig.PROVIDER_SUFFIX));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProcessName processName = (ProcessName) obj;
        String str = this.mFullProcessName;
        if (str == null) {
            return processName.mFullProcessName == null;
        }
        return str.equals(processName.mFullProcessName);
    }

    public final int hashCode() {
        String str = this.mFullProcessName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public final String toString() {
        String str = this.mFullProcessName;
        return str == null ? "<unknown>" : str;
    }

    public static ProcessName current() {
        ProcessName processName = sCachedProcessName;
        if (processName != null) {
            return processName;
        }
        ProcessName create = create(ActivityThreadHolder.getActivityThread().getProcessName());
        sCachedProcessName = create;
        if (!TextUtils.isEmpty(create.mFullProcessName)) {
            return create;
        }
        ProcessName processNameFromProcFS = getProcessNameFromProcFS();
        if (processNameFromProcFS == null) {
            return sCachedProcessName;
        }
        sCachedProcessName = processNameFromProcFS;
        return processNameFromProcFS;
    }

    private static ProcessName getProcessNameFromProcFS() {
        String readProcFileEntirely = ProcReader.readProcFileEntirely("/proc/self/cmdline");
        if (TextUtils.isEmpty(readProcFileEntirely)) {
            return null;
        }
        return create(readProcFileEntirely);
    }
}
