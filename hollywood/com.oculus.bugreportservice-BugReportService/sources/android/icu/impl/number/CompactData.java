package android.icu.impl.number;

import android.icu.impl.ICUResourceBundle;
import android.icu.impl.StandardPlural;
import android.icu.impl.UResource$Key;
import android.icu.impl.UResource$Sink;
import android.icu.impl.UResource$Table;
import android.icu.impl.UResource$Value;
import android.icu.text.CompactDecimalFormat$CompactStyle;
import android.icu.util.ICUException;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class CompactData implements MultiplierProducer {
    private boolean isEmpty = true;
    private byte largestMagnitude = 0;
    private final byte[] multipliers = new byte[16];
    private final String[] patterns = new String[(StandardPlural.COUNT * 16)];

    public enum CompactType {
        DECIMAL,
        CURRENCY
    }

    public void populate(ULocale uLocale, String str, CompactDecimalFormat$CompactStyle compactDecimalFormat$CompactStyle, CompactType compactType) {
        CompactDataSink compactDataSink = new CompactDataSink(this);
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", uLocale);
        boolean equals = str.equals("latn");
        boolean z = compactDecimalFormat$CompactStyle == CompactDecimalFormat$CompactStyle.SHORT;
        StringBuilder sb = new StringBuilder();
        getResourceBundleKey(str, compactDecimalFormat$CompactStyle, compactType, sb);
        iCUResourceBundle.getAllItemsWithFallbackNoFail(sb.toString(), compactDataSink);
        if (this.isEmpty && !equals) {
            getResourceBundleKey("latn", compactDecimalFormat$CompactStyle, compactType, sb);
            iCUResourceBundle.getAllItemsWithFallbackNoFail(sb.toString(), compactDataSink);
        }
        if (this.isEmpty && !z) {
            getResourceBundleKey(str, CompactDecimalFormat$CompactStyle.SHORT, compactType, sb);
            iCUResourceBundle.getAllItemsWithFallbackNoFail(sb.toString(), compactDataSink);
        }
        if (this.isEmpty && !equals && !z) {
            getResourceBundleKey("latn", CompactDecimalFormat$CompactStyle.SHORT, compactType, sb);
            iCUResourceBundle.getAllItemsWithFallbackNoFail(sb.toString(), compactDataSink);
        }
        if (this.isEmpty) {
            throw new ICUException("Could not load compact decimal data for locale " + uLocale);
        }
    }

    private static void getResourceBundleKey(String str, CompactDecimalFormat$CompactStyle compactDecimalFormat$CompactStyle, CompactType compactType, StringBuilder sb) {
        sb.setLength(0);
        sb.append("NumberElements/");
        sb.append(str);
        sb.append(compactDecimalFormat$CompactStyle == CompactDecimalFormat$CompactStyle.SHORT ? "/patternsShort" : "/patternsLong");
        sb.append(compactType == CompactType.DECIMAL ? "/decimalFormat" : "/currencyFormat");
    }

    public void populate(Map map) {
        for (Map.Entry entry : map.entrySet()) {
            byte length = (byte) (((String) entry.getKey()).length() - 1);
            for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                StandardPlural fromString = StandardPlural.fromString(((String) entry2.getKey()).toString());
                String str = ((String) entry2.getValue()).toString();
                this.patterns[getIndex(length, fromString)] = str;
                int countZeros = countZeros(str);
                if (countZeros > 0) {
                    this.multipliers[length] = (byte) ((countZeros - length) - 1);
                    if (length > this.largestMagnitude) {
                        this.largestMagnitude = length;
                    }
                    this.isEmpty = false;
                }
            }
        }
    }

    @Override // android.icu.impl.number.MultiplierProducer
    public int getMultiplier(int i) {
        if (i < 0) {
            return 0;
        }
        byte b = this.largestMagnitude;
        if (i > b) {
            i = b;
        }
        return this.multipliers[i];
    }

    public String getPattern(int i, StandardPlural standardPlural) {
        StandardPlural standardPlural2;
        if (i < 0) {
            return null;
        }
        byte b = this.largestMagnitude;
        if (i > b) {
            i = b;
        }
        String str = this.patterns[getIndex(i, standardPlural)];
        String str2 = (str != null || standardPlural == (standardPlural2 = StandardPlural.OTHER)) ? str : this.patterns[getIndex(i, standardPlural2)];
        if (str2 == "<USE FALLBACK>") {
            return null;
        }
        return str2;
    }

    public void getUniquePatterns(Set set) {
        set.addAll(Arrays.asList(this.patterns));
        set.remove("<USE FALLBACK>");
        set.remove(null);
    }

    private static final class CompactDataSink extends UResource$Sink {
        CompactData data;

        public CompactDataSink(CompactData compactData) {
            this.data = compactData;
        }

        @Override // android.icu.impl.UResource$Sink
        public void put(UResource$Key uResource$Key, UResource$Value uResource$Value, boolean z) {
            int countZeros;
            UResource$Table table = uResource$Value.getTable();
            for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                byte length = (byte) (uResource$Key.length() - 1);
                byte b = this.data.multipliers[length];
                UResource$Table table2 = uResource$Value.getTable();
                byte b2 = b;
                for (int i2 = 0; table2.getKeyAndValue(i2, uResource$Key, uResource$Value); i2++) {
                    StandardPlural fromString = StandardPlural.fromString(uResource$Key.toString());
                    if (this.data.patterns[CompactData.getIndex(length, fromString)] == null) {
                        String uResource$Value2 = uResource$Value.toString();
                        if (uResource$Value2.equals("0")) {
                            uResource$Value2 = "<USE FALLBACK>";
                        }
                        this.data.patterns[CompactData.getIndex(length, fromString)] = uResource$Value2;
                        if (b2 == 0 && (countZeros = CompactData.countZeros(uResource$Value2)) > 0) {
                            b2 = (byte) ((countZeros - length) - 1);
                        }
                    }
                }
                if (this.data.multipliers[length] == 0) {
                    this.data.multipliers[length] = b2;
                    if (length > this.data.largestMagnitude) {
                        this.data.largestMagnitude = length;
                    }
                    this.data.isEmpty = false;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final int getIndex(int i, StandardPlural standardPlural) {
        return (i * StandardPlural.COUNT) + standardPlural.ordinal();
    }

    /* access modifiers changed from: private */
    public static final int countZeros(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '0') {
                i++;
            } else if (i > 0) {
                break;
            }
        }
        return i;
    }
}
