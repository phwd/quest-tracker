package com.oculus.deviceauthserver;

import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import com.android.internal.annotations.Immutable;

@Immutable
final class BootCountedRealtime {
    final int mBootCount;
    final long mElapsedRealtime;

    BootCountedRealtime(int bootCount, long elapsedRealtime) {
        this.mBootCount = bootCount;
        this.mElapsedRealtime = elapsedRealtime;
    }

    /* access modifiers changed from: package-private */
    public BootCountedRealtime plus(long delta) {
        return new BootCountedRealtime(this.mBootCount, this.mElapsedRealtime + delta);
    }

    /* access modifiers changed from: package-private */
    public boolean sameBootCountAndEarlierThan(BootCountedRealtime rhs, long buffer) {
        return this.mBootCount == rhs.mBootCount && this.mElapsedRealtime < rhs.mElapsedRealtime - buffer;
    }

    static BootCountedRealtime current(Context context) {
        try {
            return new BootCountedRealtime(Settings.Global.getInt(context.getContentResolver(), "boot_count"), SystemClock.elapsedRealtime());
        } catch (Settings.SettingNotFoundException e) {
            throw new IllegalStateException("Error reading BOOT_COUNT");
        }
    }
}
