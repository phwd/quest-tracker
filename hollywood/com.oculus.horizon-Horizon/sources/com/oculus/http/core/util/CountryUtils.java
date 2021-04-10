package com.oculus.http.core.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.common.base.Strings;
import com.google.common.collect.SingletonImmutableSet;
import java.util.Locale;
import java.util.Set;

public class CountryUtils {
    public static final Set<String> BLACKLISTED_COUNTRIES = new SingletonImmutableSet("ZZ");

    public static String A00(Context context, Locale locale) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            str = telephonyManager.getSimCountryIso();
            if (Strings.isNullOrEmpty(str)) {
                str = telephonyManager.getNetworkCountryIso();
            }
        } else {
            str = null;
        }
        if (Strings.isNullOrEmpty(str)) {
            str = locale.getCountry();
        }
        if (Strings.isNullOrEmpty(str)) {
            return null;
        }
        return str.toUpperCase(Locale.US);
    }
}
