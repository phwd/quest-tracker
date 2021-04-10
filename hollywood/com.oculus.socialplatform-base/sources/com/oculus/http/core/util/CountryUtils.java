package com.oculus.http.core.util;

import X.AbstractC05710wh;
import X.AnonymousClass0th;
import X.AnonymousClass105;
import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.SingletonImmutableSet;
import com.oculus.http.core.common.CountryCode;
import com.oculus.vrshell.notifications.NotificationUri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CountryUtils {
    public static final Set<String> BLACKLISTED_COUNTRIES = new SingletonImmutableSet("ZZ");

    public static String getCurrentCountryIsoCode(Context context, Locale locale) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(NotificationUri.PHONE);
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

    public static List<CountryCode> createLocalizedCountryList(Locale locale, ImmutableList<String> immutableList) {
        String language = locale.getLanguage();
        int size = immutableList.size();
        AnonymousClass0th.A00(size, "arraySize");
        ArrayList arrayList = new ArrayList(AnonymousClass105.A00(((long) size) + 5 + ((long) (size / 10))));
        AbstractC05710wh<String> A0I = immutableList.iterator();
        while (A0I.hasNext()) {
            String next = A0I.next();
            if (!BLACKLISTED_COUNTRIES.contains(next)) {
                arrayList.add(new CountryCode(next, new Locale(language, next).getDisplayCountry(locale)));
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }
}
