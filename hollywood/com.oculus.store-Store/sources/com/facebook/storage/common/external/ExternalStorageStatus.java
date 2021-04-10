package com.facebook.storage.common.external;

import android.os.Environment;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExternalStorageStatus {
    public static boolean isAvailable() {
        String state = Environment.getExternalStorageState();
        return "mounted".equals(state) || "mounted_ro".equals(state);
    }

    public static boolean isWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
