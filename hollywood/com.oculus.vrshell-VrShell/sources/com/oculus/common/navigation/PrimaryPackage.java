package com.oculus.common.navigation;

import android.util.Log;
import com.oculus.common.build.BuildConstants;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panelservice.VerifierConstants;

public class PrimaryPackage {
    private static final String TAG = LoggingUtil.tag(PrimaryPackage.class);
    private final boolean isFocusAware;
    private final String packageName;

    public PrimaryPackage(String str, boolean z) {
        this.packageName = str;
        this.isFocusAware = z;
    }

    public boolean isSystemPackage() {
        return this.packageName.contains(BuildConstants.UID_NAME_SYSTEM_APPS) || this.packageName.contains("android.uid.systemui") || this.packageName.contains(VerifierConstants.ANDROID_SYSTEM_UI_PACKAGE_NAME);
    }

    private static boolean isShellProcessName(String str) {
        return "com.oculus.vrshell".equals(str) || "com.oculus.shellenv".equals(str);
    }

    public boolean isShell() {
        if (isShellProcessName(this.packageName)) {
            Log.d(TAG, "Shell or ShellEnv foreground");
            return true;
        }
        Log.d(TAG, "Shell backgrounded or not running.");
        return false;
    }

    public boolean isShellEnv() {
        if ("com.oculus.shellenv".equals(this.packageName)) {
            Log.d(TAG, "ShellEnv is in the foreground");
            return true;
        }
        Log.d(TAG, "ShellEnv is backgrounded or not running.");
        return false;
    }

    public boolean isFocusAware() {
        return this.isFocusAware;
    }

    public String getPackageName() {
        return this.packageName;
    }
}
