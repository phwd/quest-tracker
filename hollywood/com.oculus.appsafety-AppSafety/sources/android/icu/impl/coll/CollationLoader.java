package android.icu.impl.coll;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.util.ICUUncheckedIOException;
import android.icu.util.Output;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.io.IOException;
import java.util.MissingResourceException;

public final class CollationLoader {
    private static volatile String rootRules = null;

    private CollationLoader() {
    }

    private static void loadRootRules() {
        if (rootRules == null) {
            synchronized (CollationLoader.class) {
                if (rootRules == null) {
                    rootRules = UResourceBundle.getBundleInstance(ICUData.ICU_COLLATION_BASE_NAME, ULocale.ROOT).getString("UCARules");
                }
            }
        }
    }

    public static String getRootRules() {
        loadRootRules();
        return rootRules;
    }

    private static final class ASCII {
        private ASCII() {
        }

        static String toLowerCase(String s) {
            int i = 0;
            while (i < s.length()) {
                char c = s.charAt(i);
                if ('A' > c || c > 'Z') {
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder(s.length());
                    sb.append((CharSequence) s, 0, i);
                    sb.append((char) (c + ' '));
                    while (true) {
                        i++;
                        if (i >= s.length()) {
                            return sb.toString();
                        }
                        char c2 = s.charAt(i);
                        if ('A' <= c2 && c2 <= 'Z') {
                            c2 = (char) (c2 + ' ');
                        }
                        sb.append(c2);
                    }
                }
            }
            return s;
        }
    }

    static String loadRules(ULocale locale, String collationType) {
        return ((ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_COLLATION_BASE_NAME, locale)).getWithFallback("collations/" + ASCII.toLowerCase(collationType)).getString("Sequence");
    }

    private static final UResourceBundle findWithFallback(UResourceBundle table, String entryName) {
        return ((ICUResourceBundle) table).findWithFallback(entryName);
    }

    public static CollationTailoring loadTailoring(ULocale locale, Output<ULocale> outValidLocale) {
        String type;
        String defT;
        CollationTailoring root = CollationRoot.getRoot();
        String localeName = locale.getName();
        if (localeName.length() != 0) {
            if (!localeName.equals("root")) {
                try {
                    UResourceBundle bundle = ICUResourceBundle.getBundleInstance(ICUData.ICU_COLLATION_BASE_NAME, locale, ICUResourceBundle.OpenType.LOCALE_ROOT);
                    ULocale validLocale = (T) bundle.getULocale();
                    String validLocaleName = validLocale.getName();
                    if (validLocaleName.length() == 0 || validLocaleName.equals("root")) {
                        validLocale = (T) ULocale.ROOT;
                    }
                    outValidLocale.value = (T) validLocale;
                    try {
                        UResourceBundle collations = bundle.get("collations");
                        if (collations == null) {
                            return root;
                        }
                        String type2 = locale.getKeywordValue("collation");
                        String defaultType = "standard";
                        String defT2 = ((ICUResourceBundle) collations).findStringWithFallback("default");
                        if (defT2 != null) {
                            defaultType = defT2;
                        }
                        if (type2 == null || type2.equals("default")) {
                            type = defaultType;
                        } else {
                            type = ASCII.toLowerCase(type2);
                        }
                        UResourceBundle data = findWithFallback(collations, type);
                        if (data == null && type.length() > 6 && type.startsWith("search")) {
                            type = "search";
                            data = findWithFallback(collations, type);
                        }
                        if (data == null && !type.equals(defaultType)) {
                            type = defaultType;
                            data = findWithFallback(collations, type);
                        }
                        if (data == null && !type.equals("standard")) {
                            type = "standard";
                            data = findWithFallback(collations, type);
                        }
                        if (data == null) {
                            return root;
                        }
                        ULocale actualLocale = data.getULocale();
                        String actualLocaleName = actualLocale.getName();
                        if (actualLocaleName.length() == 0 || actualLocaleName.equals("root")) {
                            actualLocale = ULocale.ROOT;
                            if (type.equals("standard")) {
                                return root;
                            }
                        }
                        CollationTailoring t = new CollationTailoring(root.settings);
                        t.actualLocale = actualLocale;
                        try {
                            CollationDataReader.read(root, data.get("%%CollationBin").getBinary(), t);
                            try {
                                t.setRulesResource(data.get("Sequence"));
                            } catch (MissingResourceException e) {
                            }
                            if (!type.equals(defaultType)) {
                                outValidLocale.value = (T) validLocale.setKeywordValue("collation", type);
                            }
                            if (!actualLocale.equals(validLocale) && (defT = ((ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_COLLATION_BASE_NAME, actualLocale)).findStringWithFallback("collations/default")) != null) {
                                defaultType = defT;
                            }
                            if (!type.equals(defaultType)) {
                                t.actualLocale = t.actualLocale.setKeywordValue("collation", type);
                            }
                            return t;
                        } catch (IOException e2) {
                            throw new ICUUncheckedIOException("Failed to load collation tailoring data for locale:" + ((Object) actualLocale) + " type:" + type, e2);
                        }
                    } catch (MissingResourceException e3) {
                        return root;
                    }
                } catch (MissingResourceException e4) {
                    outValidLocale.value = (T) ULocale.ROOT;
                    return root;
                }
            }
        }
        outValidLocale.value = (T) ULocale.ROOT;
        return root;
    }
}
