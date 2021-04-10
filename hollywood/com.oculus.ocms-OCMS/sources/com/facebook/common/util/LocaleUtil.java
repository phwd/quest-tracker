package com.facebook.common.util;

import com.facebook.common.build.config.BuildConfig;
import com.facebook.common.string.StringUtil;
import com.google.common.collect.Iterables;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import javax.annotation.Nullable;

public class LocaleUtil {
    public static Locale languageStringToLocale(String str) {
        List<String> splitString = StringUtil.splitString(str, '_');
        if (splitString.size() == 1) {
            splitString = StringUtil.splitString(str, '-');
        }
        return new Locale((String) Iterables.getFirst(splitString, ""), (String) Iterables.get(splitString, 1, ""), (String) Iterables.get(splitString, 2, ""));
    }

    @Nullable
    public static String getISO3CountryIfAvailable(Locale locale) {
        try {
            return locale.getISO3Country();
        } catch (MissingResourceException unused) {
            return null;
        }
    }

    public static String getDisplayName(Locale locale) {
        return getDisplayName(locale, false);
    }

    public static String getDisplayName(Locale locale, boolean z) {
        String str;
        if (z) {
            str = locale.getDisplayName();
        } else {
            str = locale.getDisplayName(locale);
        }
        String locale2 = locale.toString();
        if (BuildConfig.FB_URL_SCHEME.equals(locale2)) {
            str = "FB Hash";
        } else if ("qz".equals(locale2)) {
            Locale locale3 = new Locale("my");
            String displayName = locale3.getDisplayName(locale3);
            str = ((displayName == null || displayName.isEmpty() || displayName.equals("မြန်မာ")) ? "ျမန္မာ" : "ဗမာ") + " (Zawgyi)";
        } else if ("mp".equalsIgnoreCase(locale2)) {
            str = "ꯃꯅꯤꯄꯨꯔꯤ";
        }
        return StringUtil.capitalize(str);
    }
}
