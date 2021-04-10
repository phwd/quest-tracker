package com.facebook.common.util;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.facebook.common.build.BuildConstants;
import com.facebook.common.build.config.BuildConfig;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppSchemeUriUtil {
    private static final String SERVICE_SCHEME = "fb-service";

    public static boolean isMessengerAppScheme(Uri uri) {
        return uri != null && (BuildConstants.MESSENGER_URL_SCHEME.equals(uri.getScheme()) || BuildConstants.MESSENGER_SECURE_URL_SCHEME.equals(uri.getScheme()) || BuildConstants.MESSENGER_SAMETASK_URL_SCHEME.equals(uri.getScheme()) || (BuildConstants.FB_URL_SCHEME.equals(uri.getScheme()) && "messaging".equals(uri.getHost())));
    }

    public static boolean isFacebookAppScheme(Uri uri) {
        return uri != null && (BuildConfig.FB_URL_SCHEME.equals(uri.getScheme()) || BuildConfig.MESSENGER_URL_SCHEME.equals(uri.getScheme()) || BuildConfig.MESSENGER_SECURE_URL_SCHEME.equals(uri.getScheme()) || BuildConfig.MESSENGER_SAMETASK_URL_SCHEME.equals(uri.getScheme()) || "fb-messenger-public".equals(uri.getScheme()) || BuildConfig.FBINTERNAL_URL_SCHEME.equals(uri.getScheme()) || "fb-work".equals(uri.getScheme()) || BuildConfig.DIALTONE_URL_SCHEME.equals(uri.getScheme()) || "fb-service".equals(uri.getScheme()));
    }

    public static boolean isMessengerGamesAppScheme(Uri uri) {
        return uri != null && BuildConfig.MESSENGER_URL_SCHEME.equals(uri.getScheme()) && ("quicksilver".equals(uri.getAuthority()) || "instantgames".equals(uri.getAuthority()) || "instant_games".equals(uri.getAuthority()));
    }

    public static boolean isMessengerRtcAppScheme(@Nullable Uri uri) {
        return uri != null && BuildConfig.MESSENGER_SECURE_URL_SCHEME.equals(uri.getScheme()) && ("rtccall".equals(uri.getAuthority()) || "rtc_group_call".equals(uri.getAuthority()));
    }

    public static boolean isMessengerRtcMeetupScheme(@Nullable Uri uri) {
        return uri != null && BuildConfig.MESSENGER_SECURE_URL_SCHEME.equals(uri.getScheme()) && ("meetups".equals(uri.getAuthority()) || "meetup_join".equals(uri.getAuthority()));
    }

    public static boolean isFacebookServiceScheme(Uri uri) {
        return uri != null && "fb-service".equals(uri.getScheme());
    }
}
