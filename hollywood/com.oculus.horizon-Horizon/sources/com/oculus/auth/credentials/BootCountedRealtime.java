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

    public boolean sameBootCountAndEarlierThan(BootCountedRealtime bootCountedRealtime, long j) {
        if (this.mBootCount != bootCountedRealtime.mBootCount || this.mElapsedRealtime >= bootCountedRealtime.mElapsedRealtime - j) {
            return false;
        }
        return true;
    }

    public BootCountedRealtime(int i, long j) {
        this.mBootCount = i;
        this.mElapsedRealtime = j;
    }

    public static BootCountedRealtime current(Context context) {
        return new BootCountedRealtime(currentBootCount(context), SystemClock.elapsedRealtime());
    }

    public static int currentBootCount(Context context) {
        try {
            return Settings.Global.getInt(context.getContentResolver(), "boot_count");
        } catch (Settings.SettingNotFoundException unused) {
            throw new IllegalStateException("Error reading BOOT_COUNT");
        }
    }

    public static BootCountedRealtime of(int i, long j) {
        return new BootCountedRealtime(i, j);
    }

    public static BootCountedRealtime of(Context context, long j) {
        return new BootCountedRealtime(currentBootCount(context), j);
    }
}
