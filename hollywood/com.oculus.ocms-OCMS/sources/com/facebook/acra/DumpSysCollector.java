package com.facebook.acra;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Process;
import android.os.UserManager;
import com.facebook.annotations.DoNotOptimize;
import java.util.Locale;

class DumpSysCollector {
    DumpSysCollector() {
    }

    protected static String collectMemInfo(Context context) {
        StringBuilder sb = new StringBuilder();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        Debug.MemoryInfo memoryInfo2 = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo2);
        int memoryClass = activityManager.getMemoryClass();
        float f = ((float) memoryClass) * 1024.0f;
        int i = (int) (((float) (memoryInfo2.otherPrivateDirty * 100)) / f);
        sb.append(String.format(Locale.US, "percent dalvik+native / native / d+n+other / other %d / %d / %d / %d", Integer.valueOf((int) ((((float) (memoryInfo2.nativePrivateDirty + memoryInfo2.dalvikPrivateDirty)) / f) * 100.0f)), Integer.valueOf((int) (((float) (memoryInfo2.nativePrivateDirty * 100)) / f)), Integer.valueOf((int) (((float) (((memoryInfo2.nativePrivateDirty + memoryInfo2.dalvikPrivateDirty) + memoryInfo2.otherPrivateDirty) * 100)) / f)), Integer.valueOf(i)));
        sb.append("avail/thresh/low? " + memoryInfo.availMem + "/" + memoryInfo.threshold + "/" + memoryInfo.lowMemory + "/(" + ((int) (((float) (memoryInfo.threshold * 100)) / ((float) memoryInfo.availMem))) + "%) memclass=" + memoryClass);
        sb.append("DebugMemInfo(kB): Private / Proportional / Shared");
        sb.append(String.format(Locale.US, "          dalvik: %7d / %7d / %7d", Integer.valueOf(memoryInfo2.dalvikPrivateDirty), Integer.valueOf(memoryInfo2.dalvikPss), Integer.valueOf(memoryInfo2.dalvikSharedDirty)));
        sb.append(String.format(Locale.US, "          native: %7d / %7d / %7d", Integer.valueOf(memoryInfo2.nativePrivateDirty), Integer.valueOf(memoryInfo2.nativePss), Integer.valueOf(memoryInfo2.nativeSharedDirty)));
        sb.append(String.format(Locale.US, "           other: %7d / %7d / %7d", Integer.valueOf(memoryInfo2.otherPrivateDirty), Integer.valueOf(memoryInfo2.otherPss), Integer.valueOf(memoryInfo2.otherSharedDirty)));
        sb.append(String.format(Locale.US, "GC: %d GCs, %d freed, %d free count", Integer.valueOf(Debug.getGlobalGcInvocationCount()), Integer.valueOf(Debug.getGlobalFreedSize()), Integer.valueOf(Debug.getGlobalFreedCount())));
        sb.append(String.format(Locale.US, "Native Heap: size/allocated/free %7d / %7d / %7d", Long.valueOf(Debug.getNativeHeapSize()), Long.valueOf(Debug.getNativeHeapAllocatedSize()), Long.valueOf(Debug.getNativeHeapFreeSize())));
        sb.append(String.format(Locale.US, "Threads: alloc count/alloc size/ext ac/ext as %7d / %7d / %7d / %7d", Integer.valueOf(Debug.getThreadAllocCount()), Integer.valueOf(Debug.getThreadAllocSize()), Integer.valueOf(Debug.getThreadExternalAllocCount()), Integer.valueOf(Debug.getThreadExternalAllocSize())));
        Runtime runtime = Runtime.getRuntime();
        sb.append(String.format(Locale.US, "Java Heap: size/allocated/free %7d / %7d / %7d", Long.valueOf(runtime.maxMemory()), Long.valueOf(runtime.totalMemory() - runtime.freeMemory()), Long.valueOf(runtime.freeMemory())));
        return sb.toString();
    }

    protected static String collectLargerMemoryInfo(Context context) {
        return Build.VERSION.SDK_INT >= 11 ? Api17Utils.Api11Utils.collectLargerMemoryInfo(context) : "";
    }

    protected static String collectUserInfo(Context context) {
        return Build.VERSION.SDK_INT >= 17 ? Api17Utils.collectUserInfo(context) : "";
    }

    @DoNotOptimize
    private static class Api17Utils {
        private Api17Utils() {
        }

        static String collectUserInfo(Context context) {
            return Long.toString(((UserManager) context.getSystemService("user")).getSerialNumberForUser(Process.myUserHandle()));
        }

        @DoNotOptimize
        private static class Api11Utils {
            private Api11Utils() {
            }

            static String collectLargerMemoryInfo(Context context) {
                StringBuilder sb = new StringBuilder();
                sb.append("Large heap size =" + ((ActivityManager) context.getSystemService("activity")).getLargeMemoryClass());
                return sb.toString();
            }
        }
    }
}
