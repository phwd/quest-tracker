package android.icu.impl;

import android.icu.impl.number.parse.ParsingUtils;
import android.icu.text.UnicodeSet;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.EnumMap;
import java.util.Map;

public class StaticUnicodeSets {
    private static final Map unicodeSets = new EnumMap(Key.class);

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
        Map map = unicodeSets;
        Key key = Key.DEFAULT_IGNORABLES;
        UnicodeSet unicodeSet = new UnicodeSet("[[:Zs:][\\u0009][:Bidi_Control:][:Variation_Selector:]]");
        unicodeSet.freeze();
        map.put(key, unicodeSet);
        Map map2 = unicodeSets;
        Key key2 = Key.STRICT_IGNORABLES;
        UnicodeSet unicodeSet2 = new UnicodeSet("[[:Bidi_Control:]]");
        unicodeSet2.freeze();
        map2.put(key2, unicodeSet2);
        ((ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", ULocale.ROOT)).getAllItemsWithFallback("parse", new ParseDataSink());
        Map map3 = unicodeSets;
        Key key3 = Key.OTHER_GROUPING_SEPARATORS;
        UnicodeSet unicodeSet3 = new UnicodeSet("['٬‘’＇\\u0020\\u00A0\\u2000-\\u200A\\u202F\\u205F\\u3000]");
        unicodeSet3.freeze();
        map3.put(key3, unicodeSet3);
        unicodeSets.put(Key.ALL_SEPARATORS, computeUnion(Key.COMMA, Key.PERIOD, Key.OTHER_GROUPING_SEPARATORS));
        unicodeSets.put(Key.STRICT_ALL_SEPARATORS, computeUnion(Key.STRICT_COMMA, Key.STRICT_PERIOD, Key.OTHER_GROUPING_SEPARATORS));
        Map map4 = unicodeSets;
        Key key4 = Key.PERCENT_SIGN;
        UnicodeSet unicodeSet4 = new UnicodeSet("[%٪]");
        unicodeSet4.freeze();
        map4.put(key4, unicodeSet4);
        Map map5 = unicodeSets;
        Key key5 = Key.PERMILLE_SIGN;
        UnicodeSet unicodeSet5 = new UnicodeSet("[‰؉]");
        unicodeSet5.freeze();
        map5.put(key5, unicodeSet5);
        Map map6 = unicodeSets;
        Key key6 = Key.INFINITY;
        UnicodeSet unicodeSet6 = new UnicodeSet("[∞]");
        unicodeSet6.freeze();
        map6.put(key6, unicodeSet6);
        Map map7 = unicodeSets;
        Key key7 = Key.YEN_SIGN;
        UnicodeSet unicodeSet7 = new UnicodeSet("[¥\\uffe5]");
        unicodeSet7.freeze();
        map7.put(key7, unicodeSet7);
        Map map8 = unicodeSets;
        Key key8 = Key.DIGITS;
        UnicodeSet unicodeSet8 = new UnicodeSet("[:digit:]");
        unicodeSet8.freeze();
        map8.put(key8, unicodeSet8);
        unicodeSets.put(Key.DIGITS_OR_ALL_SEPARATORS, computeUnion(Key.DIGITS, Key.ALL_SEPARATORS));
        unicodeSets.put(Key.DIGITS_OR_STRICT_ALL_SEPARATORS, computeUnion(Key.DIGITS, Key.STRICT_ALL_SEPARATORS));
    }

    public static UnicodeSet get(Key key) {
        UnicodeSet unicodeSet = (UnicodeSet) unicodeSets.get(key);
        return unicodeSet == null ? UnicodeSet.EMPTY : unicodeSet;
    }

    public static Key chooseFrom(String str, Key key) {
        if (ParsingUtils.safeContains(get(key), str)) {
            return key;
        }
        return null;
    }

    public static Key chooseFrom(String str, Key key, Key key2) {
        return ParsingUtils.safeContains(get(key), str) ? key : chooseFrom(str, key2);
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

    private static UnicodeSet computeUnion(Key key, Key key2) {
        UnicodeSet unicodeSet = new UnicodeSet();
        unicodeSet.addAll(get(key));
        unicodeSet.addAll(get(key2));
        unicodeSet.freeze();
        return unicodeSet;
    }

    private static UnicodeSet computeUnion(Key key, Key key2, Key key3) {
        UnicodeSet unicodeSet = new UnicodeSet();
        unicodeSet.addAll(get(key));
        unicodeSet.addAll(get(key2));
        unicodeSet.addAll(get(key3));
        unicodeSet.freeze();
        return unicodeSet;
    }

    /* access modifiers changed from: private */
    public static void saveSet(Key key, String str) {
        Map map = unicodeSets;
        UnicodeSet unicodeSet = new UnicodeSet(str);
        unicodeSet.freeze();
        map.put(key, unicodeSet);
    }

    static class ParseDataSink extends UResource$Sink {
        ParseDataSink() {
        }

        @Override // android.icu.impl.UResource$Sink
        public void put(UResource$Key uResource$Key, UResource$Value uResource$Value, boolean z) {
            UResource$Table table = uResource$Value.getTable();
            for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                if (!uResource$Key.contentEquals("date")) {
                    UResource$Table table2 = uResource$Value.getTable();
                    for (int i2 = 0; table2.getKeyAndValue(i2, uResource$Key, uResource$Value); i2++) {
                        boolean contentEquals = uResource$Key.contentEquals("lenient");
                        UResource$Array array = uResource$Value.getArray();
                        for (int i3 = 0; i3 < array.getSize(); i3++) {
                            array.getValue(i3, uResource$Value);
                            String uResource$Value2 = uResource$Value.toString();
                            if (uResource$Value2.indexOf(46) != -1) {
                                StaticUnicodeSets.saveSet(contentEquals ? Key.PERIOD : Key.STRICT_PERIOD, uResource$Value2);
                            } else if (uResource$Value2.indexOf(44) != -1) {
                                StaticUnicodeSets.saveSet(contentEquals ? Key.COMMA : Key.STRICT_COMMA, uResource$Value2);
                            } else if (uResource$Value2.indexOf(43) != -1) {
                                StaticUnicodeSets.saveSet(Key.PLUS_SIGN, uResource$Value2);
                            } else if (uResource$Value2.indexOf(8210) != -1) {
                                StaticUnicodeSets.saveSet(Key.MINUS_SIGN, uResource$Value2);
                            } else if (uResource$Value2.indexOf(36) != -1) {
                                StaticUnicodeSets.saveSet(Key.DOLLAR_SIGN, uResource$Value2);
                            } else if (uResource$Value2.indexOf(163) != -1) {
                                StaticUnicodeSets.saveSet(Key.POUND_SIGN, uResource$Value2);
                            } else if (uResource$Value2.indexOf(8360) != -1) {
                                StaticUnicodeSets.saveSet(Key.RUPEE_SIGN, uResource$Value2);
                            }
                        }
                    }
                }
            }
        }
    }
}
