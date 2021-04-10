package com.facebook.storage.common.external;

import android.os.Environment;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExternalStorageStatus {
    public static boolean isAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    public static boolean isWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
