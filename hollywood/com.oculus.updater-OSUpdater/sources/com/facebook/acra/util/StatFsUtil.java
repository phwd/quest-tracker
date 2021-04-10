package com.facebook.acra.util;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.facebook.annotations.DoNotOptimize;
import com.facebook.ultralight.UL;

public class StatFsUtil {
    public static final long IN_BYTE = 1;
    public static final long IN_KILO_BYTE = 1024;
    public static final long IN_MEGA_BYTE = 1048576;

    public static long getAvailableInternalStorageSpace(long j) {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return Api18Utils.getAvailableInternalStorageSpace(statFs) / j;
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
                return Api18Utils.getTotalInternalStorageSpace(statFs) / j;
            }
            return (((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize())) / j;
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long getUsedInternalStorageSpace(long j) {
        return (getTotalInternalStorageSpace(1) - getAvailableInternalStorageSpace(1)) / j;
    }

    /* access modifiers changed from: private */
    @TargetApi(UL.id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID)
    @DoNotOptimize
    public static class Api18Utils {
        private Api18Utils() {
        }

        static long getAvailableInternalStorageSpace(StatFs statFs) {
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }

        static long getTotalInternalStorageSpace(StatFs statFs) {
            return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
        }
    }
}
