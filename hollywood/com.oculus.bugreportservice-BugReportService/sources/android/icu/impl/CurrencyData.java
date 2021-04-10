package android.icu.impl;

import android.icu.text.CurrencyDisplayNames;
import android.icu.util.ULocale;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Map;

public class CurrencyData {
    public static final CurrencyDisplayInfoProvider provider;

    public static abstract class CurrencyDisplayInfo extends CurrencyDisplayNames {
        public abstract CurrencyFormatInfo getFormatInfo(String str);

        public abstract CurrencySpacingInfo getSpacingInfo();

        public abstract Map getUnitPatterns();
    }

    public interface CurrencyDisplayInfoProvider {
        CurrencyDisplayInfo getInstance(ULocale uLocale, boolean z);
    }

    public static final class CurrencyFormatInfo {
        public final String currencyPattern;
        public final String isoCode;
        public final String monetaryDecimalSeparator;
        public final String monetaryGroupingSeparator;

        public CurrencyFormatInfo(String str, String str2, String str3, String str4) {
            this.isoCode = str;
            this.currencyPattern = str2;
            this.monetaryDecimalSeparator = str3;
            this.monetaryGroupingSeparator = str4;
        }
    }

    public static final class CurrencySpacingInfo {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public static final CurrencySpacingInfo DEFAULT = new CurrencySpacingInfo("[:letter:]", "[:digit:]", " ", "[:letter:]", "[:digit:]", " ");
        public boolean hasAfterCurrency = false;
        public boolean hasBeforeCurrency = false;
        private final String[][] symbols = ((String[][]) Array.newInstance(String.class, new int[]{SpacingType.COUNT.ordinal(), SpacingPattern.COUNT.ordinal()}));

        public enum SpacingType {
            BEFORE,
            AFTER,
            COUNT
        }

        public enum SpacingPattern {
            CURRENCY_MATCH(0),
            SURROUNDING_MATCH(1),
            INSERT_BETWEEN(2),
            COUNT;
            
            static final /* synthetic */ boolean $assertionsDisabled = false;

            private SpacingPattern(int i) {
                if (!$assertionsDisabled && i != ordinal()) {
                    throw new AssertionError();
                }
            }
        }

        public CurrencySpacingInfo() {
        }

        public CurrencySpacingInfo(String... strArr) {
            if ($assertionsDisabled || strArr.length == 6) {
                int i = 0;
                int i2 = 0;
                while (i < SpacingType.COUNT.ordinal()) {
                    int i3 = i2;
                    for (int i4 = 0; i4 < SpacingPattern.COUNT.ordinal(); i4++) {
                        this.symbols[i][i4] = strArr[i3];
                        i3++;
                    }
                    i++;
                    i2 = i3;
                }
                return;
            }
            throw new AssertionError();
        }

        public void setSymbolIfNull(SpacingType spacingType, SpacingPattern spacingPattern, String str) {
            int ordinal = spacingType.ordinal();
            int ordinal2 = spacingPattern.ordinal();
            String[][] strArr = this.symbols;
            if (strArr[ordinal][ordinal2] == null) {
                strArr[ordinal][ordinal2] = str;
            }
        }

        public String[] getBeforeSymbols() {
            return this.symbols[SpacingType.BEFORE.ordinal()];
        }

        public String[] getAfterSymbols() {
            return this.symbols[SpacingType.AFTER.ordinal()];
        }
    }

    static {
        CurrencyDisplayInfoProvider currencyDisplayInfoProvider;
        try {
            currencyDisplayInfoProvider = (CurrencyDisplayInfoProvider) Class.forName("android.icu.impl.ICUCurrencyDisplayInfoProvider").newInstance();
        } catch (Throwable unused) {
            currencyDisplayInfoProvider = new CurrencyDisplayInfoProvider() {
                /* class android.icu.impl.CurrencyData.AnonymousClass1 */

                @Override // android.icu.impl.CurrencyData.CurrencyDisplayInfoProvider
                public CurrencyDisplayInfo getInstance(ULocale uLocale, boolean z) {
                    return DefaultInfo.getWithFallback(z);
                }
            };
        }
        provider = currencyDisplayInfoProvider;
    }

    public static class DefaultInfo extends CurrencyDisplayInfo {
        private static final CurrencyDisplayInfo FALLBACK_INSTANCE = new DefaultInfo(true);
        private static final CurrencyDisplayInfo NO_FALLBACK_INSTANCE = new DefaultInfo(false);
        private final boolean fallback;

        @Override // android.icu.impl.CurrencyData.CurrencyDisplayInfo
        public CurrencyFormatInfo getFormatInfo(String str) {
            return null;
        }

        private DefaultInfo(boolean z) {
            this.fallback = z;
        }

        public static final CurrencyDisplayInfo getWithFallback(boolean z) {
            return z ? FALLBACK_INSTANCE : NO_FALLBACK_INSTANCE;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public String getName(String str) {
            if (this.fallback) {
                return str;
            }
            return null;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public String getPluralName(String str, String str2) {
            if (this.fallback) {
                return str;
            }
            return null;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public String getSymbol(String str) {
            if (this.fallback) {
                return str;
            }
            return null;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public String getNarrowSymbol(String str) {
            if (this.fallback) {
                return str;
            }
            return null;
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public Map symbolMap() {
            return Collections.emptyMap();
        }

        @Override // android.icu.text.CurrencyDisplayNames
        public Map nameMap() {
            return Collections.emptyMap();
        }

        @Override // android.icu.impl.CurrencyData.CurrencyDisplayInfo
        public Map getUnitPatterns() {
            if (this.fallback) {
                return Collections.emptyMap();
            }
            return null;
        }

        @Override // android.icu.impl.CurrencyData.CurrencyDisplayInfo
        public CurrencySpacingInfo getSpacingInfo() {
            if (this.fallback) {
                return CurrencySpacingInfo.DEFAULT;
            }
            return null;
        }
    }
}
