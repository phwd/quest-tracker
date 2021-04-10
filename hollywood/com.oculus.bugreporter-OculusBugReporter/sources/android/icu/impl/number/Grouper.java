package android.icu.impl.number;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.number.PatternStringParser;
import android.icu.number.NumberFormatter;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;

public class Grouper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
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

        static {
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.MIN2.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.ON_ALIGNED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[NumberFormatter.GroupingStrategy.THOUSANDS.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public static Grouper forStrategy(NumberFormatter.GroupingStrategy grouping) {
        int i = AnonymousClass1.$SwitchMap$android$icu$number$NumberFormatter$GroupingStrategy[grouping.ordinal()];
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

    public static Grouper forProperties(DecimalFormatProperties properties) {
        if (!properties.getGroupingUsed()) {
            return GROUPER_NEVER;
        }
        short grouping12 = (short) properties.getGroupingSize();
        short grouping22 = (short) properties.getSecondaryGroupingSize();
        short minGrouping2 = (short) properties.getMinimumGroupingDigits();
        short grouping13 = (grouping12 <= 0 && grouping22 > 0) ? grouping22 : grouping12;
        return getInstance(grouping13, grouping22 > 0 ? grouping22 : grouping13, minGrouping2);
    }

    public static Grouper getInstance(short grouping12, short grouping22, short minGrouping2) {
        if (grouping12 == -1) {
            return GROUPER_NEVER;
        }
        if (grouping12 == 3 && grouping22 == 3 && minGrouping2 == 1) {
            return GROUPER_WESTERN;
        }
        if (grouping12 == 3 && grouping22 == 2 && minGrouping2 == 1) {
            return GROUPER_INDIC;
        }
        if (grouping12 == 3 && grouping22 == 3 && minGrouping2 == 2) {
            return GROUPER_WESTERN_MIN2;
        }
        if (grouping12 == 3 && grouping22 == 2 && minGrouping2 == 2) {
            return GROUPER_INDIC_MIN2;
        }
        return new Grouper(grouping12, grouping22, minGrouping2);
    }

    private static short getMinGroupingForLocale(ULocale locale) {
        return Short.valueOf(((ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, locale)).getStringWithFallback("NumberElements/minimumGroupingDigits")).shortValue();
    }

    private Grouper(short grouping12, short grouping22, short minGrouping2) {
        this.grouping1 = grouping12;
        this.grouping2 = grouping22;
        this.minGrouping = minGrouping2;
    }

    public Grouper withLocaleData(ULocale locale, PatternStringParser.ParsedPatternInfo patternInfo) {
        short minGrouping2;
        short s = this.grouping1;
        if (s != -2 && s != -4) {
            return this;
        }
        short grouping12 = (short) ((int) (patternInfo.positive.groupingSizes & 65535));
        short grouping22 = (short) ((int) ((patternInfo.positive.groupingSizes >>> 16) & 65535));
        short grouping3 = (short) ((int) ((patternInfo.positive.groupingSizes >>> 32) & 65535));
        if (grouping22 == -1) {
            grouping12 = this.grouping1 == -4 ? (short) 3 : -1;
        }
        if (grouping3 == -1) {
            grouping22 = grouping12;
        }
        short s2 = this.minGrouping;
        if (s2 == -2) {
            minGrouping2 = getMinGroupingForLocale(locale);
        } else if (s2 == -3) {
            minGrouping2 = (short) Math.max(2, (int) getMinGroupingForLocale(locale));
        } else {
            minGrouping2 = this.minGrouping;
        }
        return getInstance(grouping12, grouping22, minGrouping2);
    }

    public boolean groupAtPosition(int position, DecimalQuantity value) {
        short s = this.grouping1;
        if (s == -1 || s == 0) {
            return false;
        }
        int position2 = position - s;
        if (position2 < 0 || position2 % this.grouping2 != 0 || (value.getUpperDisplayMagnitude() - this.grouping1) + 1 < this.minGrouping) {
            return false;
        }
        return true;
    }

    public short getPrimary() {
        return this.grouping1;
    }

    public short getSecondary() {
        return this.grouping2;
    }
}
