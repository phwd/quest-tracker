package android.icu.text;

import android.icu.impl.CacheBase;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SoftCache;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;

public class NumberingSystem {
    public static final NumberingSystem LATIN = lookupInstanceByName("latn");
    private static final String[] OTHER_NS_KEYWORDS = {"native", "traditional", "finance"};
    private static CacheBase cachedLocaleData = new SoftCache() {
        /* class android.icu.text.NumberingSystem.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public NumberingSystem createInstance(String str, LocaleLookupData localeLookupData) {
            return NumberingSystem.lookupInstanceByLocale(localeLookupData);
        }
    };
    private static CacheBase cachedStringData = new SoftCache() {
        /* class android.icu.text.NumberingSystem.AnonymousClass2 */

        /* access modifiers changed from: protected */
        public NumberingSystem createInstance(String str, Void r2) {
            return NumberingSystem.lookupInstanceByName(str);
        }
    };
    private boolean algorithmic = false;
    private String desc = "0123456789";
    private String name = "latn";
    private int radix = 10;

    private static NumberingSystem getInstance(String str, int i, boolean z, String str2) {
        if (i < 2) {
            throw new IllegalArgumentException("Invalid radix for numbering system");
        } else if (z || (str2.codePointCount(0, str2.length()) == i && isValidDigitString(str2))) {
            NumberingSystem numberingSystem = new NumberingSystem();
            numberingSystem.radix = i;
            numberingSystem.algorithmic = z;
            numberingSystem.desc = str2;
            numberingSystem.name = str;
            return numberingSystem;
        } else {
            throw new IllegalArgumentException("Invalid digit string for numbering system");
        }
    }

    public static NumberingSystem getInstance(Locale locale) {
        return getInstance(ULocale.forLocale(locale));
    }

    public static NumberingSystem getInstance(ULocale uLocale) {
        String keywordValue = uLocale.getKeywordValue("numbers");
        boolean z = false;
        if (keywordValue != null) {
            String[] strArr = OTHER_NS_KEYWORDS;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = true;
                    break;
                } else if (keywordValue.equals(strArr[i])) {
                    break;
                } else {
                    i++;
                }
            }
        } else {
            keywordValue = "default";
        }
        if (z) {
            NumberingSystem instanceByName = getInstanceByName(keywordValue);
            if (instanceByName != null) {
                return instanceByName;
            }
            keywordValue = "default";
        }
        return (NumberingSystem) cachedLocaleData.getInstance(uLocale.getBaseName() + "@numbers=" + keywordValue, new LocaleLookupData(uLocale, keywordValue));
    }

    /* access modifiers changed from: private */
    public static class LocaleLookupData {
        public final ULocale locale;
        public final String numbersKeyword;

        LocaleLookupData(ULocale uLocale, String str) {
            this.locale = uLocale;
            this.numbersKeyword = str;
        }
    }

    static NumberingSystem lookupInstanceByLocale(LocaleLookupData localeLookupData) {
        NumberingSystem numberingSystem;
        String str;
        try {
            ICUResourceBundle withFallback = ((ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", localeLookupData.locale)).getWithFallback("NumberElements");
            String str2 = localeLookupData.numbersKeyword;
            while (true) {
                numberingSystem = null;
                try {
                    str = withFallback.getStringWithFallback(str2);
                    break;
                } catch (MissingResourceException unused) {
                    if (!str2.equals("native") && !str2.equals("finance")) {
                        if (!str2.equals("traditional")) {
                            str = null;
                            break;
                        }
                        str2 = "native";
                    } else {
                        str2 = "default";
                    }
                }
            }
            if (str != null) {
                numberingSystem = getInstanceByName(str);
            }
            return numberingSystem == null ? new NumberingSystem() : numberingSystem;
        } catch (MissingResourceException unused2) {
            return new NumberingSystem();
        }
    }

    public static NumberingSystem getInstanceByName(String str) {
        return (NumberingSystem) cachedStringData.getInstance(str, null);
    }

    /* access modifiers changed from: private */
    public static NumberingSystem lookupInstanceByName(String str) {
        try {
            UResourceBundle uResourceBundle = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", "numberingSystems").get("numberingSystems").get(str);
            String string = uResourceBundle.getString("desc");
            UResourceBundle uResourceBundle2 = uResourceBundle.get("radix");
            UResourceBundle uResourceBundle3 = uResourceBundle.get("algorithmic");
            int i = uResourceBundle2.getInt();
            boolean z = true;
            if (uResourceBundle3.getInt() != 1) {
                z = false;
            }
            return getInstance(str, i, z, string);
        } catch (MissingResourceException unused) {
            return null;
        }
    }

    public static boolean isValidDigitString(String str) {
        return str.codePointCount(0, str.length()) == 10;
    }

    public int getRadix() {
        return this.radix;
    }

    public String getDescription() {
        return this.desc;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAlgorithmic() {
        return this.algorithmic;
    }
}
