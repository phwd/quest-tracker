package com.facebook.acra.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

public class StatFsUtil {
    public static long getAvailableInternalStorageSpace(long unit) {
        try {
            StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return Api18Utils.getAvailableInternalStorageSpace(stat) / unit;
            }
            return (((long) stat.getAvailableBlocks()) * ((long) stat.getBlockSize())) / unit;
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getTotalInternalStorageSpace(long unit) {
        try {
            StatFs stat = new StatFs(Environment.getDataDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return Api18Utils.getTotalInternalStorageSpace(stat) / unit;
            }
            return (((long) stat.getBlockCount()) * ((long) stat.getBlockSize())) / unit;
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getUsedInternalStorageSpace(long unit) {
        return (getTotalInternalStorageSpace(1) - getAvailableInternalStorageSpace(1)) / unit;
    }

    /* access modifiers changed from: private */
    @TargetApi(18)
    public static class Api18Utils {
        static long getAvailableInternalStorageSpace(StatFs stat) {
            return stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
        }

        static long getTotalInternalStorageSpace(StatFs stat) {
            return stat.getBlockCountLong() * stat.getBlockSizeLong();
        }
    }
}
