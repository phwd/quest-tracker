package com.facebook.common.process;

import android.text.TextUtils;
import com.facebook.common.activitythreadholder.ActivityThreadHolder;
import com.facebook.common.procread.ProcReader;
import com.oculus.common.build.BuildConfig;
import javax.annotation.Nullable;

public class ProcessName {
    private static volatile ProcessName sCachedProcessName;
    @Nullable
    private final String mFullProcessName;
    @Nullable
    private final PrivateProcessName mPrivateProcessName;

    public ProcessName() {
        this(null, null);
    }

    private ProcessName(@Nullable String str, @Nullable PrivateProcessName privateProcessName) {
        this.mFullProcessName = str;
        this.mPrivateProcessName = privateProcessName;
    }

    public static ProcessName create(@Nullable String str) {
        if (str == null) {
            return new ProcessName(null, null);
        }
        String[] split = str.split(":");
        return new ProcessName(str, PrivateProcessName.createFromShortName(split.length > 1 ? split[1] : BuildConfig.PROVIDER_SUFFIX));
    }

    @Nullable
    public String getFullName() {
        return this.mFullProcessName;
    }

    @Nullable
    public String getShortPrivateName() {
        PrivateProcessName privateProcessName = this.mPrivateProcessName;
        if (privateProcessName != null) {
            return privateProcessName.getName();
        }
        return null;
    }

    @Nullable
    public PrivateProcessName getPrivateProcessName() {
        return this.mPrivateProcessName;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProcessName processName = (ProcessName) obj;
        String str = this.mFullProcessName;
        if (str != null) {
            return str.equals(processName.mFullProcessName);
        }
        if (processName.mFullProcessName == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.mFullProcessName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
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
        if (!TextUtils.isEmpty(create.getFullName())) {
            return create;
        }
        ProcessName processNameFromProcFS = getProcessNameFromProcFS();
        if (processNameFromProcFS == null) {
            return sCachedProcessName;
        }
        sCachedProcessName = processNameFromProcFS;
        return processNameFromProcFS;
    }

    @Nullable
    private static ProcessName getProcessNameFromProcFS() {
        String readProcFileEntirely = ProcReader.readProcFileEntirely("/proc/self/cmdline");
        if (TextUtils.isEmpty(readProcFileEntirely)) {
            return null;
        }
        return create(readProcFileEntirely);
    }
}
