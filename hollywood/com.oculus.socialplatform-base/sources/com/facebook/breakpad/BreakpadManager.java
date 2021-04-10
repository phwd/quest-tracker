package com.facebook.breakpad;

import X.AnonymousClass006;
import X.AnonymousClass0HJ;
import X.AnonymousClass0l0;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.facebook.acra.customdata.ProxyCustomDataStore;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.common.build.BuildConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Nullable;

public class BreakpadManager {
    public static final int BREAKPAD_DEFAULT_MINIDUMP_SIZE_LIMIT = 1536000;
    public static final String BREAKPAD_DIRECTORY = "minidumps";
    public static final String CORE_PATTERN_FILE = "/proc/sys/kernel/core_pattern";
    public static final long MD_FB_ALL_MAPS_DUMP = 128;
    public static final long MD_FB_EVERYTHING_INTERESTING = 256;
    public static final long MD_FB_EXIT_AFTER_DUMP_ATTEMPT = 8;
    @Deprecated
    public static final long MD_FB_INSTALL_ALT_STACK = 4096;
    public static final long MD_FB_NODUMP = 16;
    public static final long MD_FB_RECORD_ALL_LIBS = 1024;
    public static final long MD_FB_RECORD_STACK_PTR_LIBS = 512;
    public static final long MD_FB_STACK_PTR_MAPS_DUMP = 64;
    public static final long MD_FB_TRIVIAL_DUMP = 32;
    public static final long MD_FB_WITH_ALL_THREADS = 1;
    public static final long MD_FB_WITH_CUSTOM_STREAMS = 4;
    public static final long MD_FB_WITH_UNWINDSTACK_STREAM = 2048;
    public static final long MD_FB_WITH_UNWIND_STREAM = 2;
    public static final int SIGABRT = 6;
    public static final int SIGBUS = 7;
    public static final int SIGFPE = 8;
    public static final int SIGILL = 4;
    public static final int SIGSEGV = 11;
    public static final int SIGSTKFLT = 16;
    public static final int SIGSYS = 31;
    public static final int SIGTRAP = 5;
    public static final String TAG = "BreakpadManager";
    public static final long USE_STATIC_LIBRARY = Long.MIN_VALUE;
    public static volatile File mCrashDirectory;
    public static boolean mIsUnifiedCustomDataEnabled;
    public static String mNativeLibraryName;

    public static native boolean containsKey(String str);

    public static native void crashProcessByAssert(String str);

    public static native void crashThisProcess();

    public static native void crashThisProcessAsan();

    public static native void crashThisProcessGWPAsan();

    public static boolean disableCoreDumping() {
        ensureLoadLibrary(false);
        return disableCoreDumpingImpl();
    }

    public static native boolean disableCoreDumpingImpl();

    public static native boolean enableCoreDumpingImpl(String str);

    public static native String getCustomData(String str);

    public static native long getMinidumpFlags();

    public static native void install(String str, String str2, int i);

    public static boolean isCoreDumpingFeatureAvailable() {
        ensureLoadLibrary(false);
        return isCoreResourceHardUnlimited();
    }

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

    public static synchronized void enableUnifiedCustomData() {
        synchronized (BreakpadManager.class) {
            mIsUnifiedCustomDataEnabled = true;
        }
    }

    public static void ensureLoadLibrary(boolean z) {
        String str;
        if (mNativeLibraryName == null) {
            if (z) {
                str = "breakpad_static";
            } else {
                str = "breakpad";
            }
            AnonymousClass0l0.A06(str);
            mNativeLibraryName = str;
        }
    }

    @DoNotStrip
    public static File getCrashDirectory() {
        if (mCrashDirectory != null) {
            return mCrashDirectory;
        }
        throw new RuntimeException("Breakpad not installed");
    }

    public static Map<String, String> getCustomDataSnapshot() {
        TreeMap treeMap = new TreeMap();
        nativeGetCustomDataSnapshot(treeMap);
        return treeMap;
    }

    public static String getNativeLibraryName() {
        String str = mNativeLibraryName;
        if (str != null) {
            return str;
        }
        throw new RuntimeException("Breakpad not installed");
    }

    public static boolean isActive() {
        if (mCrashDirectory != null) {
            return true;
        }
        return false;
    }

    public static boolean isArt() {
        String property = System.getProperty("java.vm.version");
        if (property == null || property.startsWith("1.") || property.startsWith("0.")) {
            return false;
        }
        return true;
    }

    @Nullable
    public static File readCorePattern() {
        File file = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(CORE_PATTERN_FILE)));
            try {
                file = new File(bufferedReader.readLine());
            } catch (IOException e) {
                Log.e(TAG, "There was a problem reading core pattern file", e);
            }
            try {
                bufferedReader.close();
                return file;
            } catch (IOException e2) {
                Log.e(TAG, "There was a problem closing core pattern file", e2);
                return file;
            }
        } catch (FileNotFoundException e3) {
            Log.w(TAG, AnonymousClass006.A07("Core pattern file not found or blocked by SELinux", e3.getMessage()));
            return null;
        }
    }

    public static synchronized void setJvmStreamEnabled(boolean z) {
        synchronized (BreakpadManager.class) {
            boolean isArt = isArt();
            if (isArt && z) {
                AnonymousClass0l0.A06("breakpad_cpp_helper");
            }
            nativeSetJvmStreamEnabled(isArt, z);
        }
    }

    @DoNotStrip
    public static boolean enableCoreDumping(Context context) {
        if (!isCoreDumpingFeatureAvailable()) {
            return false;
        }
        return enableCoreDumpingImpl(context.getApplicationInfo().dataDir);
    }

    public static boolean isEnabledFatMinidump() {
        if ((getMinidumpFlags() & 416) != 0) {
            return true;
        }
        return false;
    }

    public static boolean simulateSignal(int i) {
        return simulateSignalDelivery(i, "");
    }

    public static boolean simulateSignal(int i, String str) {
        return simulateSignalDelivery(i, str);
    }

    public static void start(Context context) {
        start(context, 0);
    }

    public static void start(Context context, long j) {
        start(context, j, BREAKPAD_DEFAULT_MINIDUMP_SIZE_LIMIT, null);
    }

    public static synchronized void start(Context context, long j, int i, @Nullable String str) {
        synchronized (BreakpadManager.class) {
            boolean z = false;
            if ((Long.MIN_VALUE & j) != 0) {
                z = true;
            }
            ensureLoadLibrary(z);
            if (mCrashDirectory == null) {
                File dir = context.getDir("minidumps", 0);
                if (dir != null) {
                    String absolutePath = dir.getAbsolutePath();
                    if (str == null) {
                        str = "";
                    }
                    install(absolutePath, str, i);
                    mCrashDirectory = dir;
                    setMinidumpFlags(j | getMinidumpFlags() | 2 | 4);
                    if (mIsUnifiedCustomDataEnabled) {
                        ProxyCustomDataStore.Holder.CUSTOM_DATA.setDataStore(new BreakpadCustomDataStore());
                    }
                    setVersionInfo(AnonymousClass0HJ.A01(), BuildConfig.VERSION_NAME, Build.FINGERPRINT);
                    setCustomData("Fingerprint", Build.FINGERPRINT, new Object[0]);
                } else {
                    throw new RuntimeException("Breakpad init failed to create crash directory: minidumps");
                }
            }
        }
    }

    public static void start(Context context, long j, String str) {
        start(context, j, BREAKPAD_DEFAULT_MINIDUMP_SIZE_LIMIT, str);
    }
}
