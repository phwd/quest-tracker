package com.facebook.acra;

import X.AnonymousClass006;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Debug;
import android.os.Process;
import android.os.UserManager;
import com.facebook.annotations.DoNotOptimize;
import java.util.Locale;

public class DumpSysCollector {

    @DoNotOptimize
    public static class Api17Utils {

        @DoNotOptimize
        public static class Api11Utils {
            public static String collectLargerMemoryInfo(Context context) {
                StringBuilder sb = new StringBuilder();
                sb.append(AnonymousClass006.A01("Large heap size =", ((ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY)).getLargeMemoryClass()));
                return sb.toString();
            }
        }

        public static String collectUserInfo(Context context) {
            return Long.toString(((UserManager) context.getSystemService("user")).getSerialNumberForUser(Process.myUserHandle()));
        }
    }

    public static String collectMemInfo(Context context) {
        StringBuilder sb = new StringBuilder();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        Debug.MemoryInfo memoryInfo2 = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo2);
        int memoryClass = activityManager.getMemoryClass();
        int i = memoryInfo2.nativePrivateDirty;
        int i2 = memoryInfo2.dalvikPrivateDirty;
        int i3 = i + i2;
        float f = ((float) memoryClass) * 1024.0f;
        int i4 = memoryInfo2.otherPrivateDirty;
        Locale locale = Locale.US;
        sb.append(String.format(locale, "percent dalvik+native / native / d+n+other / other %d / %d / %d / %d", Integer.valueOf((int) ((((float) i3) / f) * 100.0f)), Integer.valueOf((int) (((float) (i * 100)) / f)), Integer.valueOf((int) (((float) ((i3 + i4) * 100)) / f)), Integer.valueOf((int) (((float) (i4 * 100)) / f))));
        StringBuilder sb2 = new StringBuilder("avail/thresh/low? ");
        long j = memoryInfo.availMem;
        sb2.append(j);
        sb2.append("/");
        long j2 = memoryInfo.threshold;
        sb2.append(j2);
        sb2.append("/");
        sb2.append(memoryInfo.lowMemory);
        sb2.append("/(");
        sb2.append((int) (((float) (j2 * 100)) / ((float) j)));
        sb2.append("%) memclass=");
        sb2.append(memoryClass);
        sb.append(sb2.toString());
        sb.append("DebugMemInfo(kB): Private / Proportional / Shared");
        sb.append(String.format(locale, "          dalvik: %7d / %7d / %7d", Integer.valueOf(i2), Integer.valueOf(memoryInfo2.dalvikPss), Integer.valueOf(memoryInfo2.dalvikSharedDirty)));
        sb.append(String.format(locale, "          native: %7d / %7d / %7d", Integer.valueOf(i), Integer.valueOf(memoryInfo2.nativePss), Integer.valueOf(memoryInfo2.nativeSharedDirty)));
        sb.append(String.format(locale, "           other: %7d / %7d / %7d", Integer.valueOf(i4), Integer.valueOf(memoryInfo2.otherPss), Integer.valueOf(memoryInfo2.otherSharedDirty)));
        sb.append(String.format(locale, "GC: %d GCs, %d freed, %d free count", Integer.valueOf(Debug.getGlobalGcInvocationCount()), Integer.valueOf(Debug.getGlobalFreedSize()), Integer.valueOf(Debug.getGlobalFreedCount())));
        sb.append(String.format(locale, "Native Heap: size/allocated/free %7d / %7d / %7d", Long.valueOf(Debug.getNativeHeapSize()), Long.valueOf(Debug.getNativeHeapAllocatedSize()), Long.valueOf(Debug.getNativeHeapFreeSize())));
        sb.append(String.format(locale, "Threads: alloc count/alloc size/ext ac/ext as %7d / %7d / %7d / %7d", Integer.valueOf(Debug.getThreadAllocCount()), Integer.valueOf(Debug.getThreadAllocSize()), Integer.valueOf(Debug.getThreadExternalAllocCount()), Integer.valueOf(Debug.getThreadExternalAllocSize())));
        Runtime runtime = Runtime.getRuntime();
        sb.append(String.format(locale, "Java Heap: size/allocated/free %7d / %7d / %7d", Long.valueOf(runtime.maxMemory()), Long.valueOf(runtime.totalMemory() - runtime.freeMemory()), Long.valueOf(runtime.freeMemory())));
        return sb.toString();
    }

    public static String collectLargerMemoryInfo(Context context) {
        return Api17Utils.Api11Utils.collectLargerMemoryInfo(context);
    }

    public static String collectUserInfo(Context context) {
        return Api17Utils.collectUserInfo(context);
    }
}
