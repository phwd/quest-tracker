package com.facebook.common.process;

import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.facebook.common.activitythreadholder.ActivityThreadHolder;
import com.facebook.common.procread.ProcReader;
import javax.annotation.Nullable;

public class ProcessName {
    private static final int FIRST_ISOLATED_UID = 99000;
    private static final int LAST_ISOLATED_UID = 99999;
    private static final int PER_USER_RANGE = 100000;
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
        return new ProcessName(str, PrivateProcessName.createFromShortName(split.length > 1 ? split[1] : ""));
    }

    public static ProcessName create(@Nullable String str, @Nullable String str2) {
        if (str == null) {
            return new ProcessName(null, null);
        }
        if (str2 == null || str2.equals("")) {
            return new ProcessName(str, PrivateProcessName.createFromShortName(""));
        }
        return new ProcessName(str + ":" + str2, PrivateProcessName.createFromShortName(str2));
    }

    public boolean isUnknown() {
        return this.mFullProcessName == null;
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

    public boolean isDefaultProcess() {
        return PrivateProcessName.DEFAULT_PROCESS.equals(this.mPrivateProcessName);
    }

    public static boolean isCurrentProcessIsolated() {
        int myUid;
        if (Build.VERSION.SDK_INT >= 16 && FIRST_ISOLATED_UID <= (myUid = Process.myUid() % PER_USER_RANGE) && myUid <= LAST_ISOLATED_UID) {
            return true;
        }
        return false;
    }

    @Nullable
    public String getShortNameForDiagnostics() {
        if (isUnknown()) {
            return "<unknown>";
        }
        if (isDefaultProcess()) {
            return "<default>";
        }
        PrivateProcessName privateProcessName = this.mPrivateProcessName;
        if (privateProcessName != null) {
            return privateProcessName.getName();
        }
        return null;
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

    public static void fakeProcessName(ProcessName processName) {
        sCachedProcessName = processName;
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
