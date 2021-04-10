package com.facebook.common.identifiers;

import android.os.StrictMode;
import com.facebook.infer.annotation.Nullsafe;
import java.util.UUID;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class SafeUUIDGenerator {
    public static UUID randomUUID() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return UUID.randomUUID();
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
