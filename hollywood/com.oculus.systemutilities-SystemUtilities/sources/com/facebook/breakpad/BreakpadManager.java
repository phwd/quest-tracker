package com.facebook.breakpad;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.facebook.acra.customdata.ProxyCustomDataStore;
import com.facebook.common.build.BuildConstants;
import com.facebook.soloader.SoLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;
import java.util.Map;
import java.util.TreeMap;

public class BreakpadManager {
    public static final int BREAKPAD_DEFAULT_MINIDUMP_SIZE_LIMIT = 1536000;
    private static final String BREAKPAD_DIRECTORY = "minidumps";
    private static final String CORE_PATTERN_FILE = "/proc/sys/kernel/core_pattern";
    public static final long MD_FB_ALL_MAPS_DUMP = 128;
    public static final long MD_FB_EVERYTHING_INTERESTING = 256;
    public static final long MD_FB_EXIT_AFTER_DUMP_ATTEMPT = 8;
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
    private static final String TAG = "BreakpadManager";
    public static final long USE_STATIC_LIBRARY = Long.MIN_VALUE;
    private static volatile File mCrashDirectory;
    private static boolean mIsUnifiedCustomDataEnabled = false;
    private static String mNativeLibraryName;

    public static native boolean containsKey(String str);

    public static native void crashProcessByAssert(String str);

    public static native void crashThisProcess();

    public static native void crashThisProcessAsan();

    public static native void crashThisProcessGWPAsan();

    private static native boolean disableCoreDumpingImpl();

    private static native boolean enableCoreDumpingImpl(String str);

    public static native String getCustomData(String str);

    public static native long getMinidumpFlags();

    private static native void install(String str, String str2, int i);

    private static native boolean isCoreResourceHardUnlimited();

    public static native boolean isPrivacyModeEnabled();

    private static native void nativeGetCustomDataSnapshot(TreeMap<String, String> treeMap);

    private static native void nativeSetCustomData(String str, String str2);

    private static native boolean nativeSetJvmStreamEnabled(boolean z, boolean z2);

    public static native void removeCustomData(String str);

    public static native void setMinidumpFlags(long j);

    private static native void setVersionInfo(int i, String str);

    private static native boolean simulateSignalDelivery(int i, String str);

    public static native void uninstall();

    private static void ensureLoadLibrary(boolean staticLib) {
        String nativeLibraryName;
        if (mNativeLibraryName == null) {
            if (staticLib) {
                nativeLibraryName = "breakpad_static";
            } else {
                nativeLibraryName = "breakpad";
            }
            SoLoader.loadLibrary(nativeLibraryName);
            mNativeLibraryName = nativeLibraryName;
        }
    }

    public static synchronized void start(Context context, long flags, int dumpSizeLimit, String sessionId) {
        boolean z = false;
        synchronized (BreakpadManager.class) {
            if ((Long.MIN_VALUE & flags) != 0) {
                z = true;
            }
            ensureLoadLibrary(z);
            if (mCrashDirectory == null) {
                File crashDirectory = context.getDir(BREAKPAD_DIRECTORY, 0);
                if (crashDirectory == null) {
                    throw new RuntimeException("Breakpad init failed to create crash directory: minidumps");
                }
                String absolutePath = crashDirectory.getAbsolutePath();
                if (sessionId == null) {
                    sessionId = "";
                }
                install(absolutePath, sessionId, dumpSizeLimit);
                mCrashDirectory = crashDirectory;
                setMinidumpFlags(getMinidumpFlags() | flags | 2 | 4);
                if (mIsUnifiedCustomDataEnabled) {
                    ProxyCustomDataStore.getInstance().setDataStore(new BreakpadCustomDataStore());
                }
                setVersionInfo(BuildConstants.getBuildID(), BuildConstants.getVersionName());
                setCustomData("Fingerprint", Build.FINGERPRINT, new Object[0]);
            }
        }
    }

    public static synchronized void enableUnifiedCustomData() {
        synchronized (BreakpadManager.class) {
            mIsUnifiedCustomDataEnabled = true;
        }
    }

    public static void start(Context context, long flags) {
        start(context, flags, BREAKPAD_DEFAULT_MINIDUMP_SIZE_LIMIT, null);
    }

    public static void start(Context context) {
        start(context, 0);
    }

    public static String getNativeLibraryName() {
        if (mNativeLibraryName != null) {
            return mNativeLibraryName;
        }
        throw new RuntimeException("Breakpad not installed");
    }

    public static File getCrashDirectory() {
        if (mCrashDirectory != null) {
            return mCrashDirectory;
        }
        throw new RuntimeException("Breakpad not installed");
    }

    public static boolean isActive() {
        return mCrashDirectory != null;
    }

    public static boolean enableCoreDumping(Context context) {
        if (!isCoreDumpingFeatureAvailable()) {
            return false;
        }
        return enableCoreDumpingImpl(context.getApplicationInfo().dataDir);
    }

    public static boolean disableCoreDumping() {
        ensureLoadLibrary(false);
        return disableCoreDumpingImpl();
    }

    public static boolean isCoreDumpingFeatureAvailable() {
        ensureLoadLibrary(false);
        if (Build.VERSION.SDK_INT < 24) {
            File corePattern = readCorePattern();
            File corePatternParent = corePattern != null ? corePattern.getParentFile() : null;
            if (corePattern != null && corePattern.isAbsolute() && corePatternParent != null && !corePatternParent.canWrite()) {
                Log.d(TAG, "Not write permissions into /proc/sys/kernel/core_pattern");
                return false;
            }
        }
        return isCoreResourceHardUnlimited();
    }

    public static boolean simulateSignal(int sig, String comment) {
        return simulateSignalDelivery(sig, comment);
    }

    public static boolean simulateSignal(int sig) {
        return simulateSignalDelivery(sig, "");
    }

    private static File readCorePattern() {
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader(new File(CORE_PATTERN_FILE)));
            File corePattern = null;
            try {
                corePattern = new File(bufReader.readLine());
            } catch (IOException e) {
                Log.e(TAG, "There was a problem reading core pattern file", e);
            }
            try {
                bufReader.close();
                return corePattern;
            } catch (IOException e2) {
                Log.e(TAG, "There was a problem closing core pattern file", e2);
                return corePattern;
            }
        } catch (FileNotFoundException e3) {
            Log.w(TAG, "Core pattern file not found or blocked by SELinux" + e3.getMessage());
            return null;
        }
    }

    public static synchronized void setJvmStreamEnabled(boolean enabled) {
        synchronized (BreakpadManager.class) {
            boolean isArt = isArt();
            if (isArt && enabled) {
                SoLoader.loadLibrary("breakpad_cpp_helper");
            }
            nativeSetJvmStreamEnabled(isArt, enabled);
        }
    }

    static boolean isArt() {
        String vmVersion = System.getProperty("java.vm.version");
        return vmVersion != null && !vmVersion.startsWith("1.") && !vmVersion.startsWith("0.");
    }

    public static void setCustomData(String key, String fmt, Object... args) {
        String value = fmt;
        if (args.length > 0 && fmt != null) {
            StringBuilder sb = new StringBuilder();
            Formatter formatter = new Formatter(sb);
            formatter.format(fmt, args);
            formatter.close();
            value = sb.toString();
        }
        nativeSetCustomData(key, value);
    }

    public static Map<String, String> getCustomDataSnapshot() {
        TreeMap<String, String> customData = new TreeMap<>();
        nativeGetCustomDataSnapshot(customData);
        return customData;
    }

    public static boolean isEnabledFatMinidump() {
        return (getMinidumpFlags() & 416) != 0;
    }
}
