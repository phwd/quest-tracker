package com.facebook.msys.mci;

import android.os.Environment;
import android.os.StatFs;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStripAny;

@DoNotStripAny
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceInfo {
    public static long A00(StatFs statFs) {
        return statFs.getAvailableBytes();
    }

    public static long A01(StatFs statFs) {
        return statFs.getTotalBytes();
    }

    public static long readFreeDiskSpace() {
        return A00(new StatFs(Environment.getDataDirectory().getAbsolutePath()));
    }

    public static long readTotalDiskSpace() {
        return A01(new StatFs(Environment.getDataDirectory().getAbsolutePath()));
    }
}
