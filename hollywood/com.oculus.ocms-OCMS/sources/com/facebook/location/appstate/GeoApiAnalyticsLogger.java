package com.facebook.location.appstate;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface GeoApiAnalyticsLogger {
    public static final String ANDROID_PLATFORM_LOCATION_PROVIDER = "AndroidPlatformLocationProvider";
    public static final String FACEBOOK_LOCATION_PROVIDER = "FacebookLocationProvider";
    public static final String GOOGLE_PLAY_LOCATION_PROVIDER = "GooglePlayLocationProvider";
    public static final String LOCATION_ACCESS_CACHE = "Cache";
    public static final String LOCATION_ACCESS_CONTINUOUS = "ContinuousSubscription";
    public static final String LOCATION_ACCESS_SINGLE = "SingleSubscription";

    public enum GeoApiModule {
        FbLocationManager,
        WifiScanner,
        BleScanner,
        SafeTelephonyManager
    }

    void reportGeoApiInternalUse(String str, String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool, @Nullable Long l);

    void reportGeoApiUse(String str, String str2, boolean z, boolean z2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Boolean bool);

    void reportGeoApiUse(String str, String str2, boolean z, boolean z2, boolean z3, @Nullable String str3, @Nullable String str4, @Nullable Boolean bool, @Nullable Long l, @Nullable String str5, @Nullable String str6, @Nullable Boolean bool2);

    void reportGeoApiViolatingPrivacyDecision(String str, String str2, @Nullable String str3, String str4, Boolean bool, String str5);
}
