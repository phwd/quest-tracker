package org.chromium.base;

import android.os.LocaleList;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LocaleUtils {
    public static String a(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 3365:
                if (str.equals("in")) {
                    c = 0;
                    break;
                }
                break;
            case 3374:
                if (str.equals("iw")) {
                    c = 1;
                    break;
                }
                break;
            case 3391:
                if (str.equals("ji")) {
                    c = 2;
                    break;
                }
                break;
            case 3704:
                if (str.equals("tl")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return "id";
            case 1:
                return "he";
            case 2:
                return "yi";
            case 3:
                return "fil";
            default:
                return str;
        }
    }

    public static String b(String str) {
        int indexOf = str.indexOf(45);
        if (indexOf < 0) {
            return str;
        }
        return str.substring(0, indexOf);
    }

    public static String c(Locale locale) {
        String a2 = a(locale.getLanguage());
        String country = locale.getCountry();
        if (!a2.equals("no") || !country.equals("NO") || !locale.getVariant().equals("NY")) {
            return country.isEmpty() ? a2 : AbstractC2531fV.g(a2, "-", country);
        }
        return "nn-NO";
    }

    public static String getDefaultCountryCode() {
        AbstractC1575Zv e = AbstractC1575Zv.e();
        if (e.g("default-country-code")) {
            return e.f("default-country-code");
        }
        return Locale.getDefault().getCountry();
    }

    public static String getDefaultLocaleListString() {
        LocaleList localeList = LocaleList.getDefault();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < localeList.size(); i++) {
            Locale locale = localeList.get(i);
            String language = locale.getLanguage();
            String a2 = a(language);
            if (!a2.equals(language)) {
                locale = new Locale.Builder().setLocale(locale).setLanguage(a2).build();
            }
            arrayList.add(c(locale));
        }
        return TextUtils.join(",", arrayList);
    }

    public static String getDefaultLocaleString() {
        return c(Locale.getDefault());
    }
}
