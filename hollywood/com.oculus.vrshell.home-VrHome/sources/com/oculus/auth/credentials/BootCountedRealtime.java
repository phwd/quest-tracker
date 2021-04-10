package com.oculus.auth.credentials;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import com.oculus.os.Version;
import javax.annotation.concurrent.Immutable;

@TargetApi(Version.VERSION_24)
@Immutable
public final class BootCountedRealtime {
    public final int mBootCount;
    public final long mElapsedRealtime;

    private BootCountedRealtime(int bootCount, long elapsedRealtime) {
        this.mBootCount = bootCount;
        this.mElapsedRealtime = elapsedRealtime;
    }

    public static BootCountedRealtime of(int bootCount, long elapsedRealtime) {
        return new BootCountedRealtime(bootCount, elapsedRealtime);
    }

    public static BootCountedRealtime of(Context context, long elapsedRealtime) {
        return new BootCountedRealtime(currentBootCount(context), elapsedRealtime);
    }

    public boolean sameBootCountAndEarlierThan(BootCountedRealtime rhs, long bufferMillis) {
        return this.mBootCount == rhs.mBootCount && this.mElapsedRealtime < rhs.mElapsedRealtime - bufferMillis;
    }

    public static BootCountedRealtime current(Context context) {
        return new BootCountedRealtime(currentBootCount(context), SystemClock.elapsedRealtime());
    }

    private static int currentBootCount(Context context) {
        try {
            return Settings.Global.getInt(context.getContentResolver(), "boot_count");
        } catch (Settings.SettingNotFoundException e) {
            throw new IllegalStateException("Error reading BOOT_COUNT");
        }
    }
}
