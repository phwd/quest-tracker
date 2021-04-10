package android.icu.impl.number;

import android.icu.impl.ICUResourceBundle;
import android.icu.impl.number.PatternStringParser;
import android.icu.number.NumberFormatter;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;

public class Grouper {
    private static final Grouper GROUPER_AUTO = new Grouper(-2, -2, -2);
    private static final Grouper GROUPER_INDIC = new Grouper(3, 2, 1);
    private static final Grouper GROUPER_INDIC_MIN2 = new Grouper(3, 2, 2);
    private static final Grouper GROUPER_MIN2 = new Grouper(-2, -2, -3);
    private static final Grouper GROUPER_NEVER = new Grouper(-1, -1, -2);
    private static final Grouper GROUPER_ON_ALIGNED = new Grouper(-4, -4, 1);
    private static final Grouper GROUPER_WESTERN = new Grouper(3, 3, 1);
    private static final Grouper GROUPER_WESTERN_MIN2 = new Grouper(3, 3, 2);
    private final short grouping1;
    private final short grouping2;
    private final short minGrouping;

    /* renamed from: android.icu.impl.number.Grouper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy = new int[NumberFormatter.GroupingStrategy.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                android.icu.number.NumberFormatter$GroupingStrategy[] r0 = android.icu.number.NumberFormatter.GroupingStrategy.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.impl.number.Grouper.AnonymousClass1.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy = r0
                int[] r0 = android.icu.impl.number.Grouper.AnonymousClass1.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.number.NumberFormatter$GroupingStrategy r1 = android.icu.number.NumberFormatter.GroupingStrategy.OFF     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.impl.number.Grouper.AnonymousClass1.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.number.NumberFormatter$GroupingStrategy r1 = android.icu.number.NumberFormatter.GroupingStrategy.MIN2     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.impl.number.Grouper.AnonymousClass1.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.number.NumberFormatter$GroupingStrategy r1 = android.icu.number.NumberFormatter.GroupingStrategy.AUTO     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = android.icu.impl.number.Grouper.AnonymousClass1.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy     // Catch:{ NoSuchFieldError -> 0x0035 }
                android.icu.number.NumberFormatter$GroupingStrategy r1 = android.icu.number.NumberFormatter.GroupingStrategy.ON_ALIGNED     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = android.icu.impl.number.Grouper.AnonymousClass1.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy     // Catch:{ NoSuchFieldError -> 0x0040 }
                android.icu.number.NumberFormatter$GroupingStrategy r1 = android.icu.number.NumberFormatter.GroupingStrategy.THOUSANDS     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.number.Grouper.AnonymousClass1.<clinit>():void");
        }
    }

    public static Grouper forStrategy(NumberFormatter.GroupingStrategy groupingStrategy) {
        int i = AnonymousClass1.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[groupingStrategy.ordinal()];
        if (i == 1) {
            return GROUPER_NEVER;
        }
        if (i == 2) {
            return GROUPER_MIN2;
        }
        if (i == 3) {
            return GROUPER_AUTO;
        }
        if (i == 4) {
            return GROUPER_ON_ALIGNED;
        }
        if (i == 5) {
            return GROUPER_WESTERN;
        }
        throw new AssertionError();
    }

    public static Grouper forProperties(DecimalFormatProperties decimalFormatProperties) {
        if (!decimalFormatProperties.getGroupingUsed()) {
            return GROUPER_NEVER;
        }
        short groupingSize = (short) decimalFormatProperties.getGroupingSize();
        short secondaryGroupingSize = (short) decimalFormatProperties.getSecondaryGroupingSize();
        short minimumGroupingDigits = (short) decimalFormatProperties.getMinimumGroupingDigits();
        if (groupingSize <= 0 && secondaryGroupingSize > 0) {
            groupingSize = secondaryGroupingSize;
        }
        if (secondaryGroupingSize <= 0) {
            secondaryGroupingSize = groupingSize;
        }
        return getInstance(groupingSize, secondaryGroupingSize, minimumGroupingDigits);
    }

    public static Grouper getInstance(short s, short s2, short s3) {
        if (s == -1) {
            return GROUPER_NEVER;
        }
        if (s == 3 && s2 == 3 && s3 == 1) {
            return GROUPER_WESTERN;
        }
        if (s == 3 && s2 == 2 && s3 == 1) {
            return GROUPER_INDIC;
        }
        if (s == 3 && s2 == 3 && s3 == 2) {
            return GROUPER_WESTERN_MIN2;
        }
        if (s == 3 && s2 == 2 && s3 == 2) {
            return GROUPER_INDIC_MIN2;
        }
        return new Grouper(s, s2, s3);
    }

    private static short getMinGroupingForLocale(ULocale uLocale) {
        return Short.valueOf(((ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b", uLocale)).getStringWithFallback("NumberElements/minimumGroupingDigits")).shortValue();
    }

    private Grouper(short s, short s2, short s3) {
        this.grouping1 = s;
        this.grouping2 = s2;
        this.minGrouping = s3;
    }

    public Grouper withLocaleData(ULocale uLocale, PatternStringParser.ParsedPatternInfo parsedPatternInfo) {
        short s = this.grouping1;
        if (s != -2 && s != -4) {
            return this;
        }
        long j = parsedPatternInfo.positive.groupingSizes;
        short s2 = (short) ((int) (j & 65535));
        short s3 = (short) ((int) ((j >>> 16) & 65535));
        short s4 = (short) ((int) ((j >>> 32) & 65535));
        if (s3 == -1) {
            s2 = this.grouping1 == -4 ? (short) 3 : -1;
        }
        if (s4 == -1) {
            s3 = s2;
        }
        short s5 = this.minGrouping;
        if (s5 == -2) {
            s5 = getMinGroupingForLocale(uLocale);
        } else if (s5 == -3) {
            s5 = (short) Math.max(2, (int) getMinGroupingForLocale(uLocale));
        }
        return getInstance(s2, s3, s5);
    }

    public boolean groupAtPosition(int i, DecimalQuantity decimalQuantity) {
        short s = this.grouping1;
        if (s == -1 || s == 0) {
            return false;
        }
        int i2 = i - s;
        return i2 >= 0 && i2 % this.grouping2 == 0 && (decimalQuantity.getUpperDisplayMagnitude() - this.grouping1) + 1 >= this.minGrouping;
    }

    public short getPrimary() {
        return this.grouping1;
    }

    public short getSecondary() {
        return this.grouping2;
    }
}
