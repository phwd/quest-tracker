package com.facebook.acra.util;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.facebook.breakpad.BreakpadManager;

public final class StatFsUtil {
    public static long getAvailableInternalStorageSpace(long j) {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return (statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong()) / j;
            }
            return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / j;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long getTotalInternalStorageSpace(long j) {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return (statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / j;
            }
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / j;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long getUsedInternalStorageSpace(long j) {
        return (getTotalInternalStorageSpace(1) - getAvailableInternalStorageSpace(1)) / BreakpadManager.MD_FB_RECORD_ALL_LIBS;
    }
}
