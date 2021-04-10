package com.oculus.devicelocale;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.Locale;

public class DeviceLocaleUtil {
    private static final String TAG = LoggingUtil.tag(DeviceLocaleUtil.class);

    @Nullable
    public static DeviceLocale getDeviceLocale(Context context) {
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        return getDeviceLocaleByName(locale.toString());
    }

    public static void setDeviceLocale(Context context, DeviceLocale deviceLocale) {
        String deviceLocaleLanguage = getDeviceLocaleLanguage(deviceLocale);
        String deviceLocaleCountry = getDeviceLocaleCountry(deviceLocale);
        String str = TAG;
        Log.d(str, "Setting locale to " + deviceLocaleLanguage + "_" + deviceLocaleCountry);
        Intent intent = new Intent();
        intent.setAction("companion.LOCALE");
        intent.setClassName("com.oculus.companion.server", "com.oculus.companion.server.CompanionService");
        intent.putExtra("LANGUAGE", deviceLocaleLanguage);
        intent.putExtra("COUNTRY", deviceLocaleCountry);
        intent.putExtra("USER_HANDLE", Process.myUserHandle());
        context.startService(intent);
    }

    @VisibleForTesting
    public static String getDeviceLocaleLanguage(DeviceLocale deviceLocale) {
        return deviceLocale.getLocaleName().split("_")[0];
    }

    @VisibleForTesting
    public static String getDeviceLocaleCountry(DeviceLocale deviceLocale) {
        return deviceLocale.getLocaleName().split("_")[1];
    }

    @Nullable
    @VisibleForTesting
    public static DeviceLocale getDeviceLocaleByName(String str) {
        DeviceLocale[] values = DeviceLocale.values();
        for (DeviceLocale deviceLocale : values) {
            if (deviceLocale.getLocaleName().equals(str)) {
                return deviceLocale;
            }
        }
        return null;
    }
}
