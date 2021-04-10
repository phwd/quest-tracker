package com.oculus.deviceauthserver;

import android.content.Context;
import android.content.SharedPreferences;

/* access modifiers changed from: package-private */
public final class DeviceAuthStore {
    private static final String KEY_ENT_ID = "device_ent_id";
    private static final String KEY_TOKEN_EXPIRATION_TIME_BOOT_COUNT = "device_token_expiration_time_boot_count";
    private static final String KEY_TOKEN_EXPIRATION_TIME_ELAPSED_REALTIME = "device_token_expiration_time_elapsed_realtime";
    private static final String KEY_TOKEN_VALUE = "device_token";
    private volatile DeviceCredentials mCredentials = loadCredentials(this.mPrefs);
    private final SharedPreferences mPrefs;

    DeviceAuthStore(Context context) {
        this.mPrefs = context.getSharedPreferences(DeviceAuthService.class.getName(), 0);
    }

    private static DeviceCredentials loadCredentials(SharedPreferences prefs) {
        String tokenValue = prefs.getString(KEY_TOKEN_VALUE, null);
        int bootCount = prefs.getInt(KEY_TOKEN_EXPIRATION_TIME_BOOT_COUNT, -1);
        long elapsedRealtime = prefs.getLong(KEY_TOKEN_EXPIRATION_TIME_ELAPSED_REALTIME, -1);
        String entId = prefs.getString(KEY_ENT_ID, null);
        if (tokenValue != null) {
            return new DeviceCredentials(new DeviceToken(tokenValue, new BootCountedRealtime(bootCount, elapsedRealtime)), entId);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public DeviceCredentials getCredentials() {
        return this.mCredentials;
    }

    /* access modifiers changed from: package-private */
    public void setCredentials(DeviceCredentials credentials) {
        synchronized (this.mPrefs) {
            this.mCredentials = credentials;
            BootCountedRealtime expirationTime = credentials.mToken.mExpirationTime;
            this.mPrefs.edit().putString(KEY_TOKEN_VALUE, credentials.mToken.mValue).putInt(KEY_TOKEN_EXPIRATION_TIME_BOOT_COUNT, expirationTime.mBootCount).putLong(KEY_TOKEN_EXPIRATION_TIME_ELAPSED_REALTIME, expirationTime.mElapsedRealtime).putString(KEY_ENT_ID, credentials.mEntId).apply();
        }
    }
}
