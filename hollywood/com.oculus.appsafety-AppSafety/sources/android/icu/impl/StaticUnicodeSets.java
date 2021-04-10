package android.icu.impl;

import android.icu.impl.UResource;
import android.icu.impl.number.parse.ParsingUtils;
import android.icu.text.UnicodeSet;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.EnumMap;
import java.util.Map;
import sun.security.x509.InvalidityDateExtension;

public class StaticUnicodeSets {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Map<Key, UnicodeSet> unicodeSets = new EnumMap(Key.class);

    public enum Key {
        DEFAULT_IGNORABLES,
        STRICT_IGNORABLES,
        COMMA,
        PERIOD,
        STRICT_COMMA,
        STRICT_PERIOD,
        OTHER_GROUPING_SEPARATORS,
        ALL_SEPARATORS,
        STRICT_ALL_SEPARATORS,
        MINUS_SIGN,
        PLUS_SIGN,
        PERCENT_SIGN,
        PERMILLE_SIGN,
        INFINITY,
        DOLLAR_SIGN,
        POUND_SIGN,
        RUPEE_SIGN,
        YEN_SIGN,
        DIGITS,
        DIGITS_OR_ALL_SEPARATORS,
        DIGITS_OR_STRICT_ALL_SEPARATORS
    }

    static {
        unicodeSets.put(Key.DEFAULT_IGNORABLES, new UnicodeSet("[[:Zs:][\\u0009][:Bidi_Control:][:Variation_Selector:]]").freeze());
        unicodeSets.put(Key.STRICT_IGNORABLES, new UnicodeSet("[[:Bidi_Control:]]").freeze());
        ((ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, ULocale.ROOT)).getAllItemsWithFallback("parse", new ParseDataSink());
        unicodeSets.put(Key.OTHER_GROUPING_SEPARATORS, new UnicodeSet("['٬‘’＇\\u0020\\u00A0\\u2000-\\u200A\\u202F\\u205F\\u3000]").freeze());
        unicodeSets.put(Key.ALL_SEPARATORS, computeUnion(Key.COMMA, Key.PERIOD, Key.OTHER_GROUPING_SEPARATORS));
        unicodeSets.put(Key.STRICT_ALL_SEPARATORS, computeUnion(Key.STRICT_COMMA, Key.STRICT_PERIOD, Key.OTHER_GROUPING_SEPARATORS));
        unicodeSets.put(Key.PERCENT_SIGN, new UnicodeSet("[%٪]").freeze());
        unicodeSets.put(Key.PERMILLE_SIGN, new UnicodeSet("[‰؉]").freeze());
        unicodeSets.put(Key.INFINITY, new UnicodeSet("[∞]").freeze());
        unicodeSets.put(Key.YEN_SIGN, new UnicodeSet("[¥\\uffe5]").freeze());
        unicodeSets.put(Key.DIGITS, new UnicodeSet("[:digit:]").freeze());
        unicodeSets.put(Key.DIGITS_OR_ALL_SEPARATORS, computeUnion(Key.DIGITS, Key.ALL_SEPARATORS));
        unicodeSets.put(Key.DIGITS_OR_STRICT_ALL_SEPARATORS, computeUnion(Key.DIGITS, Key.STRICT_ALL_SEPARATORS));
    }

    public static UnicodeSet get(Key key) {
        UnicodeSet candidate = unicodeSets.get(key);
        if (candidate == null) {
            return UnicodeSet.EMPTY;
        }
        return candidate;
    }

    public static Key chooseFrom(String str, Key key1) {
        if (ParsingUtils.safeContains(get(key1), str)) {
            return key1;
        }
        return null;
    }

    public static Key chooseFrom(String str, Key key1, Key key2) {
        return ParsingUtils.safeContains(get(key1), str) ? key1 : chooseFrom(str, key2);
    }

    public static Key chooseCurrency(String str) {
        if (get(Key.DOLLAR_SIGN).contains(str)) {
            return Key.DOLLAR_SIGN;
        }
        if (get(Key.POUND_SIGN).contains(str)) {
            return Key.POUND_SIGN;
        }
        if (get(Key.RUPEE_SIGN).contains(str)) {
            return Key.RUPEE_SIGN;
        }
        if (get(Key.YEN_SIGN).contains(str)) {
            return Key.YEN_SIGN;
        }
        return null;
    }

    private static UnicodeSet computeUnion(Key k1, Key k2) {
        return new UnicodeSet().addAll(get(k1)).addAll(get(k2)).freeze();
    }

    private static UnicodeSet computeUnion(Key k1, Key k2, Key k3) {
        return new UnicodeSet().addAll(get(k1)).addAll(get(k2)).addAll(get(k3)).freeze();
    }

    /* access modifiers changed from: private */
    public static void saveSet(Key key, String unicodeSetPattern) {
        unicodeSets.put(key, new UnicodeSet(unicodeSetPattern).freeze());
    }

    static class ParseDataSink extends UResource.Sink {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        ParseDataSink() {
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table contextsTable = value.getTable();
            for (int i = 0; contextsTable.getKeyAndValue(i, key, value); i++) {
                if (!key.contentEquals(InvalidityDateExtension.DATE)) {
                    UResource.Table strictnessTable = value.getTable();
                    for (int j = 0; strictnessTable.getKeyAndValue(j, key, value); j++) {
                        boolean isLenient = key.contentEquals("lenient");
                        UResource.Array array = value.getArray();
                        for (int k = 0; k < array.getSize(); k++) {
                            array.getValue(k, value);
                            String str = value.toString();
                            if (str.indexOf(46) != -1) {
                                StaticUnicodeSets.saveSet(isLenient ? Key.PERIOD : Key.STRICT_PERIOD, str);
                            } else if (str.indexOf(44) != -1) {
                                StaticUnicodeSets.saveSet(isLenient ? Key.COMMA : Key.STRICT_COMMA, str);
                            } else if (str.indexOf(43) != -1) {
                                StaticUnicodeSets.saveSet(Key.PLUS_SIGN, str);
                            } else if (str.indexOf(8210) != -1) {
                                StaticUnicodeSets.saveSet(Key.MINUS_SIGN, str);
                            } else if (str.indexOf(36) != -1) {
                                StaticUnicodeSets.saveSet(Key.DOLLAR_SIGN, str);
                            } else if (str.indexOf(163) != -1) {
                                StaticUnicodeSets.saveSet(Key.POUND_SIGN, str);
                            } else if (str.indexOf(8360) != -1) {
                                StaticUnicodeSets.saveSet(Key.RUPEE_SIGN, str);
                            }
                        }
                    }
                }
            }
        }
    }
}
