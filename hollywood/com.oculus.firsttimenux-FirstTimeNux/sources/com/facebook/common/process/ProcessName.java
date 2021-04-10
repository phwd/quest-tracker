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

    private ProcessName(@Nullable String fullProcessName, @Nullable PrivateProcessName privateProcessName) {
        this.mFullProcessName = fullProcessName;
        this.mPrivateProcessName = privateProcessName;
    }

    public static ProcessName create(@Nullable String processName) {
        String shortPrivateProcessName;
        if (processName == null) {
            return new ProcessName(null, null);
        }
        String[] parts = processName.split(":");
        if (parts.length > 1) {
            shortPrivateProcessName = parts[1];
        } else {
            shortPrivateProcessName = "";
        }
        return new ProcessName(processName, PrivateProcessName.createFromShortName(shortPrivateProcessName));
    }

    public static ProcessName create(@Nullable String packageName, @Nullable String shortPrivateName) {
        if (packageName == null) {
            return new ProcessName(null, null);
        }
        if (shortPrivateName == null || shortPrivateName.equals("")) {
            return new ProcessName(packageName, PrivateProcessName.createFromShortName(""));
        }
        return new ProcessName(packageName + ":" + shortPrivateName, PrivateProcessName.createFromShortName(shortPrivateName));
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
        if (this.mPrivateProcessName != null) {
            return this.mPrivateProcessName.getName();
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
        int appUid;
        if (Build.VERSION.SDK_INT >= 16 && FIRST_ISOLATED_UID <= (appUid = Process.myUid() % PER_USER_RANGE) && appUid <= LAST_ISOLATED_UID) {
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
        if (this.mPrivateProcessName != null) {
            return this.mPrivateProcessName.getName();
        }
        return null;
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

    public static void fakeProcessName(ProcessName fake) {
        sCachedProcessName = fake;
    }

    @Nullable
    private static ProcessName getProcessNameFromProcFS() {
        String cmdLine = ProcReader.readProcFileEntirely("/proc/self/cmdline");
        if (TextUtils.isEmpty(cmdLine)) {
            return null;
        }
        return create(cmdLine);
    }
}
