package com.facebook.breakpad;

import X.AnonymousClass006;
import X.C03160cK;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.facebook.proguard.annotations.DoNotStrip;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.TreeMap;
import javax.annotation.Nullable;

public class BreakpadManager {
    public static volatile File mCrashDirectory;
    public static String mNativeLibraryName;

    public static native boolean containsKey(String str);

    public static native void crashProcessByAssert(String str);

    public static native void crashThisProcess();

    public static native void crashThisProcessAsan();

    public static native void crashThisProcessGWPAsan();

    public static native boolean disableCoreDumpingImpl();

    public static native boolean enableCoreDumpingImpl(String str);

    public static native String getCustomData(String str);

    public static native long getMinidumpFlags();

    public static native void install(String str, String str2, int i);

    public static native boolean isCoreResourceHardUnlimited();

    public static native boolean isPrivacyModeEnabled();

    public static native void nativeGetCustomDataSnapshot(TreeMap<String, String> treeMap);

    public static native void nativeSetCustomData(String str, @Nullable String str2);

    public static native boolean nativeSetJvmStreamEnabled(boolean z, boolean z2);

    public static native void removeCustomData(String str);

    public static void setCustomData(String str, @Nullable String str2, Object... objArr) {
        if (objArr.length > 0 && str2 != null) {
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb);
            formatter.format(str2, objArr);
            formatter.close();
            str2 = sb.toString();
        }
        nativeSetCustomData(str, str2);
    }

    public static native void setMinidumpFlags(long j);

    public static native void setVersionInfo(int i, String str, String str2);

    public static native boolean simulateSignalDelivery(int i, String str);

    public static native void uninstall();

    @DoNotStrip
    public static boolean enableCoreDumping(Context context) {
        if (mNativeLibraryName == null) {
            C03160cK.A05("breakpad", 0);
            mNativeLibraryName = "breakpad";
        }
        if (Build.VERSION.SDK_INT < 24) {
            File file = null;
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/sys/kernel/core_pattern")));
                try {
                    file = new File(bufferedReader.readLine());
                } catch (IOException e) {
                    Log.e("BreakpadManager", "There was a problem reading core pattern file", e);
                }
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    Log.e("BreakpadManager", "There was a problem closing core pattern file", e2);
                }
                if (file != null) {
                    File parentFile = file.getParentFile();
                    if (file.isAbsolute() && parentFile != null && !parentFile.canWrite()) {
                        return false;
                    }
                }
            } catch (FileNotFoundException e3) {
                Log.w("BreakpadManager", AnonymousClass006.A05("Core pattern file not found or blocked by SELinux", e3.getMessage()));
            }
        }
        if (isCoreResourceHardUnlimited()) {
            return enableCoreDumpingImpl(context.getApplicationInfo().dataDir);
        }
        return false;
    }

    @DoNotStrip
    public static File getCrashDirectory() {
        if (mCrashDirectory != null) {
            return mCrashDirectory;
        }
        throw new RuntimeException("Breakpad not installed");
    }
}
