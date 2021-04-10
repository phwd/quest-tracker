package android.icu.impl.coll;

import android.icu.impl.ICUResourceBundle;
import android.icu.util.ICUUncheckedIOException;
import android.icu.util.Output;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.io.IOException;
import java.util.MissingResourceException;

public final class CollationLoader {

    private static final class ASCII {
        static String toLowerCase(String str) {
            int i = 0;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if ('A' > charAt || charAt > 'Z') {
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder(str.length());
                    sb.append((CharSequence) str, 0, i);
                    sb.append((char) (charAt + ' '));
                    while (true) {
                        i++;
                        if (i >= str.length()) {
                            return sb.toString();
                        }
                        char charAt2 = str.charAt(i);
                        if ('A' <= charAt2 && charAt2 <= 'Z') {
                            charAt2 = (char) (charAt2 + ' ');
                        }
                        sb.append(charAt2);
                    }
                }
            }
            return str;
        }
    }

    private static final UResourceBundle findWithFallback(UResourceBundle uResourceBundle, String str) {
        return ((ICUResourceBundle) uResourceBundle).findWithFallback(str);
    }

    public static CollationTailoring loadTailoring(ULocale uLocale, Output output) {
        String str;
        CollationTailoring root = CollationRoot.getRoot();
        String name = uLocale.getName();
        if (name.length() == 0 || name.equals("root")) {
            output.value = ULocale.ROOT;
            return root;
        }
        try {
            ICUResourceBundle bundleInstance = ICUResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/coll", uLocale, ICUResourceBundle.OpenType.LOCALE_ROOT);
            ULocale uLocale2 = bundleInstance.getULocale();
            String name2 = uLocale2.getName();
            if (name2.length() == 0 || name2.equals("root")) {
                uLocale2 = ULocale.ROOT;
            }
            output.value = uLocale2;
            try {
                UResourceBundle uResourceBundle = bundleInstance.get("collations");
                if (uResourceBundle == null) {
                    return root;
                }
                String keywordValue = uLocale.getKeywordValue("collation");
                String findStringWithFallback = ((ICUResourceBundle) uResourceBundle).findStringWithFallback("default");
                if (findStringWithFallback == null) {
                    findStringWithFallback = "standard";
                }
                String lowerCase = (keywordValue == null || keywordValue.equals("default")) ? findStringWithFallback : ASCII.toLowerCase(keywordValue);
                UResourceBundle findWithFallback = findWithFallback(uResourceBundle, lowerCase);
                if (findWithFallback == null && lowerCase.length() > 6 && lowerCase.startsWith("search")) {
                    findWithFallback = findWithFallback(uResourceBundle, "search");
                    lowerCase = "search";
                }
                if (findWithFallback == null && !lowerCase.equals(findStringWithFallback)) {
                    findWithFallback = findWithFallback(uResourceBundle, findStringWithFallback);
                    lowerCase = findStringWithFallback;
                }
                if (findWithFallback == null && !lowerCase.equals("standard")) {
                    findWithFallback = findWithFallback(uResourceBundle, "standard");
                    lowerCase = "standard";
                }
                if (findWithFallback == null) {
                    return root;
                }
                ULocale uLocale3 = findWithFallback.getULocale();
                String name3 = uLocale3.getName();
                if (name3.length() == 0 || name3.equals("root")) {
                    uLocale3 = ULocale.ROOT;
                    if (lowerCase.equals("standard")) {
                        return root;
                    }
                }
                CollationTailoring collationTailoring = new CollationTailoring(root.settings);
                collationTailoring.actualLocale = uLocale3;
                try {
                    CollationDataReader.read(root, findWithFallback.get("%%CollationBin").getBinary(), collationTailoring);
                    try {
                        collationTailoring.setRulesResource(findWithFallback.get("Sequence"));
                    } catch (MissingResourceException unused) {
                    }
                    if (!lowerCase.equals(findStringWithFallback)) {
                        output.value = uLocale2.setKeywordValue("collation", lowerCase);
                    }
                    if (uLocale3.equals(uLocale2) || (str = ((ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/coll", uLocale3)).findStringWithFallback("collations/default")) == null) {
                        str = findStringWithFallback;
                    }
                    if (!lowerCase.equals(str)) {
                        collationTailoring.actualLocale = collationTailoring.actualLocale.setKeywordValue("collation", lowerCase);
                    }
                    return collationTailoring;
                } catch (IOException e) {
                    throw new ICUUncheckedIOException("Failed to load collation tailoring data for locale:" + uLocale3 + " type:" + lowerCase, e);
                }
            } catch (MissingResourceException unused2) {
                return root;
            }
        } catch (MissingResourceException unused3) {
            output.value = ULocale.ROOT;
            return root;
        }
    }
}
