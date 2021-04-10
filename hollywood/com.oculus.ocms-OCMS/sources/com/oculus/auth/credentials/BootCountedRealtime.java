package com.oculus.auth.credentials;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import javax.annotation.concurrent.Immutable;

@TargetApi(24)
@Immutable
public final class BootCountedRealtime {
    public final int mBootCount;
    public final long mElapsedRealtime;

    private BootCountedRealtime(int i, long j) {
        this.mBootCount = i;
        this.mElapsedRealtime = j;
    }

    public static BootCountedRealtime of(int i, long j) {
        return new BootCountedRealtime(i, j);
    }

    public static BootCountedRealtime of(Context context, long j) {
        return new BootCountedRealtime(currentBootCount(context), j);
    }

    public boolean sameBootCountAndEarlierThan(BootCountedRealtime bootCountedRealtime, long j) {
        return this.mBootCount == bootCountedRealtime.mBootCount && this.mElapsedRealtime < bootCountedRealtime.mElapsedRealtime - j;
    }

    public static BootCountedRealtime current(Context context) {
        return new BootCountedRealtime(currentBootCount(context), SystemClock.elapsedRealtime());
    }

    private static int currentBootCount(Context context) {
        try {
            return Settings.Global.getInt(context.getContentResolver(), "boot_count");
        } catch (Settings.SettingNotFoundException unused) {
            throw new IllegalStateException("Error reading BOOT_COUNT");
        }
    }
}
