package com.oculus.http.core.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.oculus.http.core.common.CountryCode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CountryUtils {
    private static final Set<String> BLACKLISTED_COUNTRIES = ImmutableSet.of("ZZ");

    public static List<CountryCode> createLocalizedCountryList(Locale locale, ImmutableList<String> immutableList) {
        String language = locale.getLanguage();
        ArrayList newArrayListWithExpectedSize = Lists.newArrayListWithExpectedSize(immutableList.size());
        UnmodifiableIterator<String> it = immutableList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!BLACKLISTED_COUNTRIES.contains(next)) {
                newArrayListWithExpectedSize.add(new CountryCode(next, new Locale(language, next).getDisplayCountry(locale)));
            }
        }
        Collections.sort(newArrayListWithExpectedSize);
        return newArrayListWithExpectedSize;
    }

    public static String getCurrentCountryIsoCode(Context context, Locale locale) {
        String str;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            String simCountryIso = telephonyManager.getSimCountryIso();
            str = Strings.isNullOrEmpty(simCountryIso) ? telephonyManager.getNetworkCountryIso() : simCountryIso;
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
